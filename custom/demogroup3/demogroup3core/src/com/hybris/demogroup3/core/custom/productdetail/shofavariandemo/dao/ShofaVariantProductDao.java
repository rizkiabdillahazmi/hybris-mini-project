package com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.dao;

import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;

import java.util.List;

/**
 * The Class ShofaVarianProductDao
 *
 * @author Adi Sugiarto at 12/8/2022
 */

public interface ShofaVariantProductDao {

    List<ShofaVariantProductDemoModel> getShofaVariantByMerk(String merk);
}
