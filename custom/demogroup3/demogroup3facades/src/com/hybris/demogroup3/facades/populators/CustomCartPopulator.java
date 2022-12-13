package com.hybris.demogroup3.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.GroupOrderEntryPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.core.model.order.AbstractOrderModel;

public class CustomCartPopulator extends GroupOrderEntryPopulator {

    @Override
    public void populate(final AbstractOrderModel source, final AbstractOrderData target)
    {
        target.setNote(source.getNote());
        super.populate(source, target);
    }
}
