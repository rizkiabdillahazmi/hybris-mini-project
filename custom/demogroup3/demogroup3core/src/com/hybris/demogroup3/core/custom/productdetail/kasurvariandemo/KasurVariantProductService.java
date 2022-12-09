package com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo;

import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;

import java.util.List;

/**
 * The Class KasurVariantProductDemoService
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public interface KasurVariantProductService {
    List<KasurVariantProductDemoModel> getKasurVariantByMerk(String merk);
}
