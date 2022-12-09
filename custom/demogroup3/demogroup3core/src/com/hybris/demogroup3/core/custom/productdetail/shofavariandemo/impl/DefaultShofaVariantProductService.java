package com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.impl;

import com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.ShofaVariantProductService;
import com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao.ShofaVariantProductDao;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;

import javax.annotation.Resource;
import java.util.List;

/**
 * The Class DefaultShofaVariantProductService
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class DefaultShofaVariantProductService implements ShofaVariantProductService {

    @Resource(name = "shofaVariantProductDao")
    private ShofaVariantProductDao shofaVariantProductDao;

    @Override
    public List<ShofaVariantProductDemoModel> getShofaVariantByMerk(String merk) {
        return shofaVariantProductDao.getShofaVariantByMerk(merk);
    }
}
