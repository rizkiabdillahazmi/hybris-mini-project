package com.hybris.demogroup3.core.testimony.dao.impl;

import com.hybris.demogroup3.core.testimony.dao.TestimonyDao;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultTestimonyDao implements TestimonyDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<CustomerReviewModel> getAllIntheboxTestimony() {
        final String query = "SELECT {cr:pk} FROM {CustomerReview AS cr JOIN Product AS p ON {cr.product}={p.pk} JOIN User AS u ON {cr.user}={u.pk} JOIN Catalog AS c ON {p.catalog}={c.pk}} WHERE {c.id} LIKE 'demoGroup3ProductCatalog'";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        final SearchResult<CustomerReviewModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<CustomerReviewModel> customerReviewModels = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(customerReviewModels)) {
            return customerReviewModels;
        }
        return Collections.emptyList();
    }
}
