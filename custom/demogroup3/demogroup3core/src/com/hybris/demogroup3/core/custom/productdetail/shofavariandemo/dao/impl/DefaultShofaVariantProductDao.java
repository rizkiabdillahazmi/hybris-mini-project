package com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao.impl;

import com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao.ShofaVariantProductDao;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * The Class DefaultShofaVariantProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public class DefaultShofaVariantProductDao implements ShofaVariantProductDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    private final String SELECT_SHOFA_VARIANT = "select {s.pk} from {ShofaVariantProductDemo as s} ";

    @Override
    public List<ShofaVariantProductDemoModel> getShofaVariantByMerk(String merk) {
        final String query = SELECT_SHOFA_VARIANT +
                "where {s.merk} = ?merk";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("code", merk);
        final SearchResult<ShofaVariantProductDemoModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<ShofaVariantProductDemoModel> models = searchResult.getResult();

        if (CollectionUtils.isNotEmpty(models)) {
            return models;
        }
        return Collections.emptyList();
    }
}
