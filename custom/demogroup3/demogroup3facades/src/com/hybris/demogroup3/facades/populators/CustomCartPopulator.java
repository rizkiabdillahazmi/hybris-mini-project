package com.hybris.demogroup3.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.CartPopulator;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.order.CartModel;

public class CustomCartPopulator extends CartPopulator{
    @Override
    public void populate(final CartModel source, final CartData target)
    {
        target.setNote(source.getNote());
        super.populate(source, target);
    }
}
