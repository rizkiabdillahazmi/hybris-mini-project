/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.demogroup3.storefront.controllers.cms;

import de.hybris.platform.acceleratorfacades.productcarousel.ProductCarouselFacade;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import com.hybris.demogroup3.storefront.controllers.ControllerConstants;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import de.hybris.platform.converters.ConfigurablePopulator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for CMS ProductReferencesComponent.
 */
@Controller("ProductCarouselComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.ProductCarouselComponent)
public class ProductCarouselComponentController extends AbstractAcceleratorCMSComponentController<ProductCarouselComponentModel>
{
	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.DESCRIPTION);

	private Converter<ProductModel, ProductData> productConverter;

//	@Resource(name = "productCarouselFacade")
//	private ProductCarouselFacade productCarouselFacade;

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductCarouselComponentModel component)
	{
		final List<ProductData> products = new ArrayList<>();

		products.addAll(collectLinkedProducts(component));
		products.addAll(collectSearchProducts(component));

		model.addAttribute("title", component.getTitle());
		model.addAttribute("productData", products);
	}

	protected List<ProductData> collectLinkedProducts(final ProductCarouselComponentModel component)
	{
//		return customProductCarouselFacade.collectProducts(component);
		final List<ProductData> products = new ArrayList<>();

		for (final ProductModel productModel : component.getProducts())
		{
			products.add(productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS));
		}

		for (final CategoryModel categoryModel : component.getCategories())
		{
			for (final ProductModel productModel : categoryModel.getProducts())
			{
				products.add(productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS));
			}
		}

		return products;
	}

	protected List<ProductData> collectSearchProducts(final ProductCarouselComponentModel component)
	{
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(component.getSearchQuery());
		final String categoryCode = component.getCategoryCode();

		if (searchQueryData.getValue() != null && categoryCode != null)
		{
			final SearchStateData searchState = new SearchStateData();
			searchState.setQuery(searchQueryData);

			final PageableData pageableData = new PageableData();
			pageableData.setPageSize(100); // Limit to 100 matching results

			return productSearchFacade.categorySearch(categoryCode, searchState, pageableData).getResults();
		}

		return Collections.emptyList();
	}
}
