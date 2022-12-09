package com.hybris.demogroup3.core.custom.productdetail.shofavariandemo;

import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;

import java.util.List;

/**
 * The Class ShofaVariantProductService
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public interface ShofaVariantProductService {
    List<ShofaVariantProductDemoModel> getShofaVariantByMerk(String merk);
}
