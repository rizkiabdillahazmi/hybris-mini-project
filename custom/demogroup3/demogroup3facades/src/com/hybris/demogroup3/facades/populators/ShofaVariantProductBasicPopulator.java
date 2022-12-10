package com.hybris.demogroup3.facades.populators;

import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import com.hybris.demogroup3.facades.product.data.ShofaVariantProductData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * The Class ShofaVariantProductBasicPopulator
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class ShofaVariantProductBasicPopulator implements Populator<ShofaVariantProductDemoModel, ShofaVariantProductData> {

    @Resource
    private CommercePriceService commercePriceService;

    @Resource
    private PriceDataFactory priceDataFactory;

    @Resource
    private UrlResolver<ProductModel> productModelUrlResolver;

    @Override
    public void populate(ShofaVariantProductDemoModel source, ShofaVariantProductData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setMerk(source.getMerk());
        target.setName(source.getName());
        target.setWarna(source.getWarna());
        target.setKeterangan(source.getKeterangan());
        target.setPageUrl(productModelUrlResolver.resolve(source));

        final PriceDataType priceType;
        final PriceInformation info;
        if (CollectionUtils.isEmpty(source.getVariants())) {
            priceType = PriceDataType.BUY;
            info = commercePriceService.getWebPriceForProduct(source);
        }
        else {
            priceType = PriceDataType.FROM;
            info = commercePriceService.getFromPriceForProduct(source);
        }

        if (info != null) {
            final PriceData priceData = priceDataFactory.create(priceType, BigDecimal.valueOf(info.getPriceValue().getValue()),
                    info.getPriceValue().getCurrencyIso());
            target.setPriceData(priceData);
        }

        MediaModel img = source.getPicture();
        if (null != img) {
            target.setImgUrl(img.getURL());
        }

    }
}
