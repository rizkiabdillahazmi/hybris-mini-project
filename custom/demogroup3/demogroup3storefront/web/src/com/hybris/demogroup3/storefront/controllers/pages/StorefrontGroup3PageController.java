package com.hybris.demogroup3.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StorefrontGroup3PageController extends AbstractPageController {

    private static final String TEMPLATE_ABOUT_PAGE = "storefrontGroup3AboutPage";

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String getAboutPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_ABOUT_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

    private static final String TEMPLATE_TESTIMONI_PAGE = "storefrontGroup3TestimoniPage";

    @RequestMapping(value = "testimoni", method = RequestMethod.GET)
    public String getTestimoniPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_TESTIMONI_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }
}
