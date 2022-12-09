package com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.dao;

import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;

import java.util.List;

/**
 * The Class KasurVarianProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public interface KasurVariantProductDao {
    List<KasurVariantProductDemoModel> getKasurVariantByMerk(String merk);
}
