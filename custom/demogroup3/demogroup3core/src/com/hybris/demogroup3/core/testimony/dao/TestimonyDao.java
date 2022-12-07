package com.hybris.demogroup3.core.testimony.dao;

import de.hybris.platform.customerreview.model.CustomerReviewModel;

import java.util.List;

public interface TestimonyDao {
        List<CustomerReviewModel> getAllIntheboxTestimony();
}