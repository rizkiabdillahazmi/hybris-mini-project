package com.hybris.demogroup3.storefront.controllers.pages;

import com.hybris.demogroup3.facades.testimony.impl.DefaultTestimonyFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
//@RequestMapping("/")
public class StorefrontGroup3PageController extends AbstractPageController {

    @Resource(name = "defaultTestimonyFacade")
    private DefaultTestimonyFacade defaultTestimonyFacade;

    private static final String TEMPLATE_ABOUT_PAGE = "storefrontGroup3AboutPage";

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAboutPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_ABOUT_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

    private static final String TEMPLATE_TESTIMONI_PAGE = "storefrontGroup3TestimoniPage";

    @RequestMapping(value = "/testimoni", method = RequestMethod.GET)
    public String getTestimoniPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_TESTIMONI_PAGE);
        model.addAttribute("allTestimony", defaultTestimonyFacade.getAllIntheboxTestimony());
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

    private static final String TEMPLATE_ABOUT_WHAT_PAGE = "storefrontGroup3AboutWhatPage";

    @RequestMapping(value = "/apa-itu-kasur-inthebox", method = RequestMethod.GET)
    public String getAboutWhatPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_ABOUT_WHAT_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

    private static final String TEMPLATE_ABOUT_WHY_PAGE = "storefrontGroup3AboutWhyPage";

    @RequestMapping(value = "/alasan-pilih-kasur-inthebox", method = RequestMethod.GET)
    public String getAboutWhyPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_ABOUT_WHY_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

    private static final String TEMPLATE_ABOUT_HOW_PAGE = "storefrontGroup3AboutHowPage";

    @RequestMapping(value = "/cara-beli-inthebox", method = RequestMethod.GET)
    public String getAboutHowPage(final Model model) throws CMSItemNotFoundException{
        final ContentPageModel group3Data =getContentPageForLabelOrId(TEMPLATE_ABOUT_HOW_PAGE);
        storeCmsPageInModel(model, group3Data);
        setUpMetaDataForContentPage(model, group3Data);
        return getViewForPage(model);
    }

}
