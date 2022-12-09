package com.hybris.demogroup3.facades.populators;

import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.facades.product.data.KasurVariantProductData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import org.apache.commons.collections.CollectionUtils;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * The Class KasurVariantProductBasicPopulator
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class KasurVariantProductBasicPopulator implements Populator<KasurVariantProductDemoModel, KasurVariantProductData> {

    @Resource
    private CommercePriceService commercePriceService;

    @Resource
    private PriceDataFactory priceDataFactory;

    private static final Logger LOG = Logger.getLogger(KasurVariantProductBasicPopulator.class);

    @Override
    public void populate(KasurVariantProductDemoModel source, KasurVariantProductData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setMerk(source.getMerk());
        target.setName(source.getName());
        target.setUkuran(source.getUkuran());
        target.setKeterangan(source.getKeterangan());
        

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
