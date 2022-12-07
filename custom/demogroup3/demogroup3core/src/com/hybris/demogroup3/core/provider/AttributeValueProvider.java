package com.hybris.demogroup3.core.provider;

import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttributeValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {
    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
        if (model instanceof ShofaVariantProductDemoModel)
        {
            final ShofaVariantProductDemoModel product = (ShofaVariantProductDemoModel) model;
            final Collection<FieldValue> fieldValues = new ArrayList<>();
            fieldValues.addAll(createFieldValueShofa(product, indexedProperty));
            return fieldValues;
        }
        if (model instanceof KasurVariantProductDemoModel)
        {
            final KasurVariantProductDemoModel product = (KasurVariantProductDemoModel) model;
            final Collection<FieldValue> fieldValues = new ArrayList<>();
            fieldValues.addAll(createFieldValueKasur(product, indexedProperty));
            return fieldValues;
        }
        else
        {
            throw new FieldValueProviderException("Cannot get attribute");
        }
    }

    protected List<FieldValue> createFieldValueShofa(final ShofaVariantProductDemoModel product, final IndexedProperty indexedProperty)
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
        if (null != product && null != product.getWarna()  )
        {
            for (final String fieldName : fieldNames)
            {
                fieldValues.add(new FieldValue(fieldName, product.getWarna()));
            }
        }
        return fieldValues;
    }

    protected List<FieldValue> createFieldValueKasur(final KasurVariantProductDemoModel product, final IndexedProperty indexedProperty)
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
        if (null != product && null != product.getUkuran()  )
        {
            for (final String fieldName : fieldNames)
            {
                fieldValues.add(new FieldValue(fieldName, product.getUkuran()));
            }
        }
        return fieldValues;
    }


    /**
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider()
    {
        return fieldNameProvider;
    }

    /**
     * @param fieldNameProvider
     *           the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }
}
