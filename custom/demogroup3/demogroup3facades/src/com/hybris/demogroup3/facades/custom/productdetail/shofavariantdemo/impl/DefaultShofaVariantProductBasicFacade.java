package com.hybris.demogroup3.facades.custom.productdetail.shofavariantdemo.impl;

import com.hybris.demogroup3.core.custom.productdetail.shofavariandemo.ShofaVariantProductService;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import com.hybris.demogroup3.facades.custom.productdetail.shofavariantdemo.ShofaVariantProductBasicFacade;
import com.hybris.demogroup3.facades.product.data.ShofaVariantProductData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class DefaultShofaVariantProductBasicFacade
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class DefaultShofaVariantProductBasicFacade implements ShofaVariantProductBasicFacade {

    @Resource(name = "shofaVariantProductService")
    private ShofaVariantProductService shofaVariantProductService;

    @Resource(name = "shofaVariantBasicConverter")
    private Converter<ShofaVariantProductDemoModel, ShofaVariantProductData> shofaVariantBasicConverter;

    @Override
    public List<ShofaVariantProductData> getOtherShofaVariant(String merk, String code) {

        if (null != merk) {
            List<ShofaVariantProductDemoModel> models = shofaVariantProductService.getOtherShofaVariant(merk, code);
            List<ShofaVariantProductData> datas = new ArrayList<>();

            if (CollectionUtils.isNotEmpty(models)) {
                datas.addAll(shofaVariantBasicConverter.convertAll(models));
                return datas;
            }
        }
        return Collections.emptyList();
    }
}
