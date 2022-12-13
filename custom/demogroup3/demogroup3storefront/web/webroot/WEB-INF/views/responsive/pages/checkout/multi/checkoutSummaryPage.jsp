<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/checkout/multi/summary/placeOrder" var="placeOrderUrl" htmlEscape="false"/>
<spring:url value="/checkout/multi/termsAndConditions" var="getTermsAndConditionsUrl" htmlEscape="false"/>
<c:url value="/cart" var="cartUrl"/>

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
<h1 class="checkout-title">Checkout</h1>
<div class="container">
    <div class="checkout-border">
        <div class="row">
            <div class="col-lg-7">
                <div class="checkout-headline">
                    <span class="glyphicon glyphicon-lock"></span>
                    <spring:theme code="checkout.multi.secure.checkout" />
                </div>
                <multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
                    <ycommerce:testId code="checkoutStepFour">
                        <div class="checkout-review hidden-xs">
                            <div class="checkout-order-summary">
                                <h3 class="summary-title">Detail Pengiriman </h3>
                                <div class="summary-address">
                                <multi-checkout:shipmentItems cartData="${cartData}" showDeliveryAddress="true" />
                                </div>
                                <div class="note-text">
                                      <span class="note-bold">Catatan</span> = ${cartData.note}
                                 </div>
                                 <hr>
                                <multi-checkout:orderTotals cartData="${cartData}" showTaxEstimate="${showTaxEstimate}" showTax="${showTax}" subtotalsCssClasses="dark"/>
                            </div>
                        </div>
                        <div class="place-order-form hidden-xs">
                            <form:form action="${placeOrderUrl}" id="placeOrderForm1" modelAttribute="placeOrderForm">
                                <div class="checkbox">
                                    <label> <form:checkbox id="Terms1" path="termsCheck" />
                                        <spring:theme var="termsAndConditionsHtml" code="checkout.summary.placeOrder.readTermsAndConditions" arguments="${fn:escapeXml(getTermsAndConditionsUrl)}" htmlEscape="false"/>
                                        ${ycommerce:sanitizeHTML(termsAndConditionsHtml)}
                                    </label>
                                </div>
                                <a href="${fn:escapeXml(cartUrl)}" class="back-to-cart"> < Kembali ke Keranjang Belanja </a>
                                <button id="placeOrder" type="submit" class="btn-place-order btn-order">
                                    <spring:theme code="checkout.summary.placeOrder" text="Place Order"/>
                                </button>
                            </form:form>
                        </div>
                    </ycommerce:testId>
                </multi-checkout:checkoutSteps>
            </div>

            <div class="col-lg-5">
                <multi-checkout:checkoutOrderSummary cartData="${cartData}" showDeliveryAddress="true" showPaymentInfo="true" showTaxEstimate="true" showTax="true" />
            </div>
        </div>
    </div>
</div>
</template:page>
