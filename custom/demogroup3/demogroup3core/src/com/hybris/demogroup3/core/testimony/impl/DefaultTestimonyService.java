package com.hybris.demogroup3.core.testimony.impl;

import com.hybris.demogroup3.core.testimony.TestimonyService;
import com.hybris.demogroup3.core.testimony.dao.TestimonyDao;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

import javax.annotation.Resource;
import java.util.List;

public class DefaultTestimonyService implements TestimonyService {
    @Resource(name = "testimonyDao")
    private TestimonyDao testimonyDao;
    @Override
    public List<CustomerReviewModel> getAllIntheboxTestimony() {
        return testimonyDao.getAllIntheboxTestimony();
    }
}
