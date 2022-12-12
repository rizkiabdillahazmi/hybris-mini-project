package com.hybris.demogroup3.storefront.controllers.pages;

import com.hybris.demogroup3.storefront.cartdetail.CustomCartPageForm;
import de.hybris.platform.acceleratorstorefrontcommons.strategy.impl.NoOpCartRestorationStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * The Class CustomCartPageFormController
 *
 * @author Adi Sugiarto at 12/12/2022
 */

@Controller
@RequestMapping(value = "/cart/custom")
public class CustomCartPageFormController {

    @Resource
    private CartService cartService;

    @Resource
    protected ModelService modelService;

    private static final Logger LOG = Logger.getLogger(CustomCartPageFormController.class);

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateCart(final CustomCartPageForm form) {

        CartModel cart = cartService.getSessionCart();
        cart.setNote(form.getNote());

        try {
            LOG.info("++++++++++++++++++ form note ++++++++++++++++++++++" + form.getNote() + "++++++++++++++++++++++++++++++++++++++++++++++++++");
            cart.setNote(form.getNote());
            modelService.save(cart);


        } catch (Exception ex) {

            LOG.error("Couldn't insert cart note: " + cart.getSessionId() + ".", ex);
        }

    }

}
