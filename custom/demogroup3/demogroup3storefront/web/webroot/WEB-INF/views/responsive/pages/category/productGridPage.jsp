<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>

<template:page pageTitle="${pageTitle}">

	<cms:pageSlot position="Section1" var="feature" element="div" class="product-grid-section1-slot">
		<cms:component component="${feature}" element="div" class="yComponentWrapper map product-grid-section1-component"/>
	</cms:pageSlot>

	<div class="container px-0 image-header">
          <img src="https://inthebox.net/images/layout-v2/catalog-header.png">
    </div>
	<div class="container">
        <div class="section-header mt-4">PILIHAN PRODUK INTHEBOX</div>
    </div>
    <div class ="first__item"><hr></hr></div>

	<div class="row ">
		<div class="col-md-3">
			<cms:pageSlot position="ProductLeftRefinements" var="feature" element="div" class="product-grid-left-refinements-slot">
				<cms:component component="${feature}" element="div" class="yComponentWrapper product-grid-left-refinements-component"/>
			</cms:pageSlot>
		</div>
		<div class="col-md-9">
			<cms:pageSlot position="ProductGridSlot" var="feature" element="div" class="product-grid-right-result-slot">
				<cms:component component="${feature}" element="div" class="product__list--wrapper yComponentWrapper product-grid-right-result-component"/>
			</cms:pageSlot>
		</div>
	</div>
	<storepickup:pickupStorePopup />
</template:page>