package com.hybris.demogroup3.facades.custom.productdetail.kasurvariantdemo.impl;

import com.hybris.demogroup3.core.custom.productdetail.kasurvariandemo.KasurVariantProductService;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.facades.custom.productdetail.kasurvariantdemo.KasurVariantProductBasicFacade;
import com.hybris.demogroup3.facades.product.data.KasurVariantProductData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class DefaultKasurVariantProductFacade
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class DefaultKasurVariantProductBasicFacade implements KasurVariantProductBasicFacade {

    @Resource(name = "kasurVariantProductService")
    private KasurVariantProductService kasurVariantProductService;

    @Resource(name = "kasurVariantBasicConverter")
    private Converter<KasurVariantProductDemoModel, KasurVariantProductData> kasurVariantBasicConverter;

    @Override
    public List<KasurVariantProductData> getKasurVariantByMerk(String merk) {
        List<KasurVariantProductDemoModel> models = kasurVariantProductService.getKasurVariantByMerk(merk);
        List<KasurVariantProductData> datas = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(models)) {
            datas.addAll(kasurVariantBasicConverter.convertAll(models));
            return datas;
        }
        return Collections.emptyList();
    }
}
