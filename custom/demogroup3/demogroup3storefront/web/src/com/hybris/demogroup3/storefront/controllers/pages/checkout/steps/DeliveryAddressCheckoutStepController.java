/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.demogroup3.storefront.controllers.pages.checkout.steps;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateQuoteCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.ValidationResults;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.checkout.steps.AbstractCheckoutStepController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.acceleratorstorefrontcommons.util.AddressDataUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.util.Config;
import com.hybris.demogroup3.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.VoucherForm;

import de.hybris.platform.commerceservices.security.BruteForceAttackHandler;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/checkout/multi/delivery-address")
public class DeliveryAddressCheckoutStepController extends AbstractCheckoutStepController
{
	private static final String DELIVERY_ADDRESS = "delivery-address";
	private static final String SHOW_SAVE_TO_ADDRESS_BOOK_ATTR = "showSaveToAddressBook";

	private static final Logger LOGGER = Logger.getLogger(DeliveryAddressCheckoutStepController.class);

	public static final String VOUCHER_FORM = "voucherForm";


	@Resource(name = "bruteForceAttackHandler")
	private BruteForceAttackHandler bruteForceAttackHandler;

	@Resource(name = "voucherFacade")
	private VoucherFacade voucherFacade;

	@Resource(name = "addressDataUtil")
	private AddressDataUtil addressDataUtil;

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@RequireHardLogIn
	@PreValidateQuoteCheckoutStep
	@PreValidateCheckoutStep(checkoutStep = DELIVERY_ADDRESS)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		getCheckoutFacade().setDeliveryAddressIfAvailable();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		populateCommonModelAttributes(model, cartData, new AddressForm());

		if (!model.containsAttribute(VOUCHER_FORM))
		{
			model.addAttribute(VOUCHER_FORM, new VoucherForm());
		}

		return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@RequireHardLogIn
	public String add(final AddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		getAddressValidator().validate(addressForm, bindingResult);
		populateCommonModelAttributes(model, cartData, addressForm);

		if (!model.containsAttribute(VOUCHER_FORM))
		{
			model.addAttribute(VOUCHER_FORM, new VoucherForm());
		}

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "address.error.formentry.invalid");
			return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		final AddressData newAddress = addressDataUtil.convertToAddressData(addressForm);

		processAddressVisibilityAndDefault(addressForm, newAddress);

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		getUserFacade().addAddress(newAddress);

		final AddressData previousSelectedAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
		// Set the new address as the selected checkout delivery address
		getCheckoutFacade().setDeliveryAddress(newAddress);
		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		{ // temporary address should be removed
			getUserFacade().removeAddress(previousSelectedAddress);
		}

		// Set the new address as the selected checkout delivery address
		getCheckoutFacade().setDeliveryAddress(newAddress);

		return getCheckoutStep().nextStep();
	}

	protected void processAddressVisibilityAndDefault(final AddressForm addressForm, final AddressData newAddress)
	{
		if (addressForm.getSaveInAddressBook() != null)
		{
			newAddress.setVisibleInAddressBook(addressForm.getSaveInAddressBook().booleanValue());
			if (addressForm.getSaveInAddressBook().booleanValue() && CollectionUtils.isEmpty(getUserFacade().getAddressBook()))
			{
				newAddress.setDefaultAddress(true);
			}
		}
		else if (getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			newAddress.setDefaultAddress(true);
			newAddress.setVisibleInAddressBook(true);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editAddressForm(@RequestParam("editAddressCode") final String editAddressCode, final Model model,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		final ValidationResults validationResults = getCheckoutStep().validate(redirectAttributes);
		if (getCheckoutStep().checkIfValidationErrors(validationResults))
		{
			return getCheckoutStep().onValidation(validationResults);
		}

		AddressData addressData = null;
		if (StringUtils.isNotEmpty(editAddressCode))
		{
			addressData = getCheckoutFacade().getDeliveryAddressForCode(editAddressCode);
		}

		final AddressForm addressForm = new AddressForm();
		final boolean hasAddressData = addressData != null;
		if (hasAddressData)
		{
			addressDataUtil.convert(addressData, addressForm);
		}

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		populateCommonModelAttributes(model, cartData, addressForm);

		if (addressData != null)
		{
			model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.valueOf(!addressData.isVisibleInAddressBook()));
		}

		return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@RequireHardLogIn
	public String edit(final AddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult);

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		populateCommonModelAttributes(model, cartData, addressForm);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "address.error.formentry.invalid");
			return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		final AddressData newAddress = addressDataUtil.convertToAddressData(addressForm);

		processAddressVisibility(addressForm, newAddress);

		newAddress.setDefaultAddress(CollectionUtils.isEmpty(getUserFacade().getAddressBook())
				|| getUserFacade().getAddressBook().size() == 1 || Boolean.TRUE.equals(addressForm.getDefaultAddress()));

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			if (StringUtils.isNotEmpty(addressForm.getAddressId()))
			{
				final AddressData addressData = getCheckoutFacade().getDeliveryAddressForCode(addressForm.getAddressId());
				if (addressData != null)
				{
					model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.valueOf(!addressData.isVisibleInAddressBook()));
					model.addAttribute("edit", Boolean.TRUE);
				}
			}

			return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		getUserFacade().editAddress(newAddress);
		getCheckoutFacade().setDeliveryModeIfAvailable();
		getCheckoutFacade().setDeliveryAddress(newAddress);

		return getCheckoutStep().nextStep();
	}

	protected void processAddressVisibility(final AddressForm addressForm, final AddressData newAddress)
	{

		if (addressForm.getSaveInAddressBook() == null)
		{
			newAddress.setVisibleInAddressBook(true);
		}
		else
		{
			newAddress.setVisibleInAddressBook(Boolean.TRUE.equals(addressForm.getSaveInAddressBook()));
		}
	}

	@RequestMapping(value = "/remove", method =
	{ RequestMethod.GET, RequestMethod.POST }) //NOSONAR
	@RequireHardLogIn
	public String removeAddress(@RequestParam("addressCode") final String addressCode, final RedirectAttributes redirectModel,
			final Model model) throws CMSItemNotFoundException
	{
		if (getCheckoutFacade().isRemoveAddressEnabledForCart())
		{
			final AddressData addressData = new AddressData();
			addressData.setId(addressCode);
			getUserFacade().removeAddress(addressData);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"account.confirmation.address.removed");
		}
		final ContentPageModel multiCheckoutSummaryPage = getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, multiCheckoutSummaryPage);
		setUpMetaDataForContentPage(model, multiCheckoutSummaryPage);
		model.addAttribute("addressForm", new AddressForm());

		return getCheckoutStep().currentStep();
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@RequireHardLogIn
	public String doSelectSuggestedAddress(final AddressForm addressForm, final RedirectAttributes redirectModel)
	{
		final Set<String> resolveCountryRegions = org.springframework.util.StringUtils
				.commaDelimitedListToSet(Config.getParameter("resolve.country.regions"));

		final AddressData selectedAddress = addressDataUtil.convertToAddressData(addressForm);
		final CountryData countryData = selectedAddress.getCountry();

		if (!resolveCountryRegions.contains(countryData.getIsocode()))
		{
			selectedAddress.setRegion(null);
		}

		if (addressForm.getSaveInAddressBook() != null)
		{
			selectedAddress.setVisibleInAddressBook(addressForm.getSaveInAddressBook().booleanValue());
		}

		if (Boolean.TRUE.equals(addressForm.getEditAddress()))
		{
			getUserFacade().editAddress(selectedAddress);
		}
		else
		{
			getUserFacade().addAddress(selectedAddress);
		}

		final AddressData previousSelectedAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
		// Set the new address as the selected checkout delivery address
		getCheckoutFacade().setDeliveryAddress(selectedAddress);
		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		{ // temporary address should be removed
			getUserFacade().removeAddress(previousSelectedAddress);
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "checkout.multi.address.added");

		return getCheckoutStep().nextStep();
	}


	/**
	 * This method gets called when the "Use this Address" button is clicked. It sets the selected delivery address on
	 * the checkout facade - if it has changed, and reloads the page highlighting the selected delivery address.
	 *
	 * @param selectedAddressCode
	 *           - the id of the delivery address.
	 *
	 * @return - a URL to the page to load.
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@RequireHardLogIn
	public String doSelectDeliveryAddress(@RequestParam("selectedAddressCode") final String selectedAddressCode,
			final RedirectAttributes redirectAttributes)
	{
		final ValidationResults validationResults = getCheckoutStep().validate(redirectAttributes);
		if (getCheckoutStep().checkIfValidationErrors(validationResults))
		{
			return getCheckoutStep().onValidation(validationResults);
		}
		if (StringUtils.isNotBlank(selectedAddressCode))
		{
			final AddressData selectedAddressData = getCheckoutFacade().getDeliveryAddressForCode(selectedAddressCode);
			final boolean hasSelectedAddressData = selectedAddressData != null;
			if (hasSelectedAddressData)
			{
				setDeliveryAddress(selectedAddressData);
			}
		}
		return getCheckoutStep().nextStep();
	}

	protected void setDeliveryAddress(final AddressData selectedAddressData)
	{
		final AddressData cartCheckoutDeliveryAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
		if (isAddressIdChanged(cartCheckoutDeliveryAddress, selectedAddressData))
		{
			getCheckoutFacade().setDeliveryAddress(selectedAddressData);
			if (cartCheckoutDeliveryAddress != null && !cartCheckoutDeliveryAddress.isVisibleInAddressBook())
			{ // temporary address should be removed
				getUserFacade().removeAddress(cartCheckoutDeliveryAddress);
			}
		}
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

	protected String getBreadcrumbKey()
	{
		return "checkout.multi." + getCheckoutStep().getProgressBarId() + ".breadcrumb";
	}

	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(DELIVERY_ADDRESS);
	}

	protected void populateCommonModelAttributes(final Model model, final CartData cartData, final AddressForm addressForm)
			throws CMSItemNotFoundException
	{
		model.addAttribute("cartData", cartData);
		model.addAttribute("addressForm", addressForm);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		model.addAttribute("noAddress", Boolean.valueOf(getCheckoutFlowFacade().hasNoDeliveryAddress()));
		model.addAttribute("addressFormEnabled", Boolean.valueOf(getCheckoutFacade().isNewAddressEnabledForCart()));
		model.addAttribute("removeAddressEnabled", Boolean.valueOf(getCheckoutFacade().isRemoveAddressEnabledForCart()));
		model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.TRUE);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, getResourceBreadcrumbBuilder().getBreadcrumbs(getBreadcrumbKey()));
		model.addAttribute("metaRobots", "noindex,nofollow");
		if (StringUtils.isNotBlank(addressForm.getCountryIso()))
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
		}
		prepareDataForPage(model);
		final ContentPageModel multiCheckoutSummaryPage = getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, multiCheckoutSummaryPage);
		setUpMetaDataForContentPage(model, multiCheckoutSummaryPage);
		setCheckoutStepLinksForModel(model, getCheckoutStep());
	}

	@RequestMapping(value = "/voucher/apply", method = RequestMethod.POST)
	public String applyVoucherAction(@Valid final VoucherForm form, final BindingResult bindingResult,
									 final HttpServletRequest request, final RedirectAttributes redirectAttributes)
	{
		try
		{
			if (bindingResult.hasErrors())
			{
				redirectAttributes.addFlashAttribute("errorMsg",
						getMessageSource().getMessage("text.voucher.apply.invalid.error", null, getI18nService().getCurrentLocale()));
			}
			else
			{
				final String ipAddress = request.getRemoteAddr();
				if (bruteForceAttackHandler.registerAttempt(ipAddress + "_voucher"))
				{
					redirectAttributes.addFlashAttribute("disableUpdate", Boolean.valueOf(true));
					redirectAttributes.addFlashAttribute("errorMsg",
							getMessageSource().getMessage("text.voucher.apply.bruteforce.error", null, getI18nService().getCurrentLocale()));
				}
				else
				{
					voucherFacade.applyVoucher(form.getVoucherCode());
					redirectAttributes.addFlashAttribute("successMsg",
							getMessageSource().getMessage("text.voucher.apply.applied.success", new Object[]
									{ form.getVoucherCode() }, getI18nService().getCurrentLocale()));
				}
			}
		}
		catch (final VoucherOperationException e)
		{
			redirectAttributes.addFlashAttribute(VOUCHER_FORM, form);
			redirectAttributes.addFlashAttribute("errorMsg",
					getMessageSource().getMessage(e.getMessage(), null,
							getMessageSource().getMessage("text.voucher.apply.invalid.error", null, getI18nService().getCurrentLocale()),
							getI18nService().getCurrentLocale()));
			if (LOGGER.isDebugEnabled())
			{
				LOGGER.debug(e.getMessage(), e);
			}

		}

		return getCheckoutStep().currentStep();
	}


	@RequestMapping(value = "/voucher/remove", method = RequestMethod.POST)
	public String removeVoucher(@Valid final VoucherForm form, final RedirectAttributes redirectModel)
	{
		try
		{
			voucherFacade.releaseVoucher(form.getVoucherCode());
		}
		catch (final VoucherOperationException e)
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "text.voucher.release.error",
					new Object[]
							{ form.getVoucherCode() });
			if (LOGGER.isDebugEnabled())
			{
				LOGGER.debug(e.getMessage(), e);
			}

		}
		return getCheckoutStep().currentStep();
	}
}
