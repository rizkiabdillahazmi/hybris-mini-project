package com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao.impl;

import com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao.KasurVariantProductDao;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.variants.model.VariantProductModel;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class DefaultKasurVariantProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public class DefaultKasurVariantProductDao implements KasurVariantProductDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<KasurVariantProductDemoModel> getOtherKasurVariant(String merk, String code) {
        final String query = "select {k.pk} from " +
                             "{KasurVariantProductDemo as k join Product as p on {p.pk} = {k.baseproduct}} " +
                             "where {k.merk} = ?merk " +
                             "and {k.code} <> ?code";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("merk", merk);
        params.put("code", code);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(KasurVariantProductDemoModel.class));
        final SearchResult searchResult = flexibleSearchService.search(searchQuery);
        List<KasurVariantProductDemoModel> models = searchResult.getResult();

        if (CollectionUtils.isNotEmpty(models)) {
            return models;
        }
        return Collections.emptyList();
    }
}
