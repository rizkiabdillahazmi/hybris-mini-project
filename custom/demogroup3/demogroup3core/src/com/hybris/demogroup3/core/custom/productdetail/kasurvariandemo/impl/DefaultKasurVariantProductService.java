package com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.impl;

import com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.KasurVariantProductService;
import com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao.KasurVariantProductDao;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;

import javax.annotation.Resource;
import java.util.List;

/**
 * The Class DefaultKasurVariantProductService
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class DefaultKasurVariantProductService implements KasurVariantProductService {

    @Resource(name = "kasurVariantProductDao")
    private KasurVariantProductDao kasurVariantProductDao;

    @Override
    public List<KasurVariantProductDemoModel> getOtherKasurVariant(String merk, String code) {
        return kasurVariantProductDao.getOtherKasurVariant(merk, code);
    }
}
