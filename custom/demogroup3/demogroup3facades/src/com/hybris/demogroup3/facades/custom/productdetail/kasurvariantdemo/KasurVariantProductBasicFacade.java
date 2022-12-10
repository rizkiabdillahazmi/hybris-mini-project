package com.hybris.demogroup3.facades.custom.productdetail.kasurvariantdemo;

import com.hybris.demogroup3.facades.product.data.KasurVariantProductData;

import java.util.List;

/**
 * The Class KasurVariantProductFacade
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public interface KasurVariantProductBasicFacade {

    List<KasurVariantProductData> getOtherKasurVariant(String merk, String code);

}
