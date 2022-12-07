package com.hybris.demogroup3.facades.register;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import org.springframework.util.Assert;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCustomCustomerFacade extends DefaultCustomerFacade {
    @Override
    public void register(final RegisterData registerData) throws DuplicateUidException
    {
        validateParameterNotNullStandardMessage("registerData", registerData);
        Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
        Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
        Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

        final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
        setCommonPropertiesForRegister(registerData, newCustomer);
        getCustomerAccountService().register(newCustomer, registerData.getPassword());
    }

    protected void setCommonPropertiesForRegister(final RegisterData registerData, final CustomerModel customerModel)
    {
        customerModel.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
        setTitleForRegister(registerData, customerModel);
        setUidForRegister(registerData, customerModel);
        customerModel.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
        customerModel.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
        customerModel.setId(registerData.getId());
    }
}
