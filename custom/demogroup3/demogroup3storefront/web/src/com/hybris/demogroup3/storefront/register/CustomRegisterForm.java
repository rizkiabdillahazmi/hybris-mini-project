package com.hybris.demogroup3.storefront.register;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

public class CustomRegisterForm extends RegisterForm {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}