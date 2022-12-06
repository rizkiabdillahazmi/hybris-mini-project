package com.hybris.demogroup3.facades.testimony.impl;

import com.hybris.demogroup3.core.testimony.TestimonyService;
import com.hybris.demogroup3.facades.testimony.TestimonyFacade;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import org.apache.commons.collections.CollectionUtils;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultTestimonyFacade implements TestimonyFacade {
    @Resource(name = "defaultCustomerReviewConverter")
    private Converter<CustomerReviewModel, ReviewData> defaultCustomerReviewConverter;

    @Resource(name = "testimonyService")
    private TestimonyService testimonyService;
    @Override
    public List<ReviewData> getAllIntheboxTestimony() {
        List<CustomerReviewModel> customerReviewModels = testimonyService.getAllIntheboxTestimony();
        if (CollectionUtils.isNotEmpty(customerReviewModels)) {
            return defaultCustomerReviewConverter.convertAll(customerReviewModels);
        }
        return Collections.emptyList();
    }
}
