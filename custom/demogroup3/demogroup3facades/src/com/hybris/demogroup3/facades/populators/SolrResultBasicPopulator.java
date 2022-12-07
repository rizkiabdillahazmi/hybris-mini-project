package com.hybris.demogroup3.facades.populators;

import de.hybris.platform.catalog.jalo.CatalogUnawareMedia;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.cmsfacades.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SolrResultBasicPopulator extends SearchResultProductPopulator {

    @Override
    public void populate(SearchResultValueData source, de.hybris.platform.commercefacades.product.data.ProductData target) {
        super.populate(source, target);
        target.setMerk(this.<String>getValue(source,"merk"));
        target.setUkuran(this.<String>getValue(source,"ukuran"));
        target.setAttribute(this.<String>getValue(source,"attribute"));
        target.setWarna(this.<String>getValue(source,"warna"));

    }

}
