package com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao.impl;

import com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao.KasurVariantProductDao;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * The Class DefaultKasurVariantProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public class DefaultKasurVariantProductDao implements KasurVariantProductDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    private final String SELECT_KASUR_VARIANT = "select {k.pk} from {KasurVariantProductDemo as k} ";

    @Override
    public List<KasurVariantProductDemoModel> getKasurVariantByMerk(String merk) {
        final String query = SELECT_KASUR_VARIANT +
                "where {k.merk} like ?merk";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("merk", merk);
        final SearchResult<KasurVariantProductDemoModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<KasurVariantProductDemoModel> models = searchResult.getResult();

        if (CollectionUtils.isNotEmpty(models)) {
            return models;
        }
        return Collections.emptyList();
    }
}
