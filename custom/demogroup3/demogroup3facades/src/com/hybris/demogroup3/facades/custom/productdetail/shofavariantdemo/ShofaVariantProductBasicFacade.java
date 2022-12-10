package com.hybris.demogroup3.facades.custom.productdetail.shofavariantdemo;

import com.hybris.demogroup3.facades.product.data.ShofaVariantProductData;

import java.util.List;

/**
 * The Class ShofaVariantProductBasicFacade
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public interface ShofaVariantProductBasicFacade {

    List<ShofaVariantProductData> getOtherShofaVariant(String merk, String code);

}
