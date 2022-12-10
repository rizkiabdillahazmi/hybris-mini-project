package com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao.impl;

import com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao.ShofaVariantProductDao;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class DefaultShofaVariantProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public class DefaultShofaVariantProductDao implements ShofaVariantProductDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<ShofaVariantProductDemoModel> getOtherShofaVariant(String merk, String code) {
        final String query = "select {s.pk} from " +
                "{ShofaVariantProductDemo as s join Product as p on {p.pk} = {s.baseproduct}} " +
                "where {s.merk} = ?merk " +
                "and {s.code} <> ?code";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("merk", merk);
        params.put("code", code);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(ShofaVariantProductDemoModel.class));
        final SearchResult searchResult = flexibleSearchService.search(searchQuery);
        List<ShofaVariantProductDemoModel> models = searchResult.getResult();

        if (CollectionUtils.isNotEmpty(models)) {
            return models;
        }
        return Collections.emptyList();
    }
}
