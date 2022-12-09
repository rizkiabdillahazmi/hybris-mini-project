package com.hybris.demogroup3.facades.populators;

import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.facades.product.data.KasurVariantProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import org.apache.commons.collections.CollectionUtils;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * The Class KasurVariantProductBasicPopulator
 *
 * @author Adi Sugiarto at 12/9/2022
 */

public class KasurVariantProductBasicPopulator implements Populator<KasurVariantProductDemoModel, KasurVariantProductData> {

    private final String CURRENCY_IDR = "IDR";

    private static final Logger LOG = Logger.getLogger(KasurVariantProductBasicPopulator.class);

    @Override
    public void populate(KasurVariantProductDemoModel source, KasurVariantProductData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setMerk(source.getMerk());
        target.setName(source.getName());
        target.setUkuran(source.getUkuran());

        Collection<PriceRowModel> priceRows = source.getEurope1Prices();
        if (CollectionUtils.isNotEmpty(priceRows))
        {
            for (final PriceRowModel priceRow : priceRows)
            {
                if (priceRow.getCurrency().getIsocode().equalsIgnoreCase(CURRENCY_IDR))
                {
                    LOG.info("This is price ++++++++++++++++++++++++++++++++++++ : " + priceRow);
                }
            }
        }

        MediaModel img = source.getPicture();
        if (null != img) {
            target.setImgUrl(img.getURL());
        }

    }
}
