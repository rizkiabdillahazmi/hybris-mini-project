/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.demogroup3.storefront.controllers.pages.checkout.steps;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateQuoteCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.checkout.steps.AbstractCheckoutStepController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import com.hybris.demogroup3.storefront.controllers.ControllerConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hybris.platform.commerceservices.security.BruteForceAttackHandler;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.acceleratorstorefrontcommons.forms.VoucherForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/checkout/multi/delivery-method")
public class DeliveryMethodCheckoutStepController extends AbstractCheckoutStepController
{
	private static final String DELIVERY_METHOD = "delivery-method";

	public static final String VOUCHER_FORM = "voucherForm";

	private static final Logger LOGGER = Logger.getLogger(DeliveryMethodCheckoutStepController.class);


	@Resource(name = "bruteForceAttackHandler")
	private BruteForceAttackHandler bruteForceAttackHandler;

	@Resource(name = "voucherFacade")
	private VoucherFacade voucherFacade;

	@RequestMapping(value = "/choose", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	@PreValidateQuoteCheckoutStep
	@PreValidateCheckoutStep(checkoutStep = DELIVERY_METHOD)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		// Try to set default delivery mode
		getCheckoutFacade().setDeliveryModeIfAvailable();

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		this.prepareDataForPage(model);
		final ContentPageModel multiCheckoutSummaryPage = getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, multiCheckoutSummaryPage);
		setUpMetaDataForContentPage(model, multiCheckoutSummaryPage);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		setCheckoutStepLinksForModel(model, getCheckoutStep());

		if (!model.containsAttribute(VOUCHER_FORM))
		{
			model.addAttribute(VOUCHER_FORM, new VoucherForm());
		}

		return ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage;
	}

	/**
	 * This method gets called when the "Use Selected Delivery Method" button is clicked. It sets the selected delivery
	 * mode on the checkout facade and reloads the page highlighting the selected delivery Mode.
	 *
	 * @param selectedDeliveryMethod
	 *           - the id of the delivery mode.
	 * @return - a URL to the page to load.
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@RequireHardLogIn
	public String doSelectDeliveryMode(@RequestParam("delivery_method") final String selectedDeliveryMethod)
	{
		if (StringUtils.isNotEmpty(selectedDeliveryMethod))
		{
			getCheckoutFacade().setDeliveryMode(selectedDeliveryMethod);
		}

		return getCheckoutStep().nextStep();
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

	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(DELIVERY_METHOD);
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
