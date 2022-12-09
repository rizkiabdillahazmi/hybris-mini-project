<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

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
                <h3 class="subtitle-checkout"> Informasi Pengiriman </h3>
                <multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
                    <jsp:body>
                        <ycommerce:testId code="checkoutStepOne">
                            <div class="checkout-shipping">

                                    <div class="checkout-indent">
                                        <div class="headline"><spring:theme code="checkout.summary.shippingAddress" /></div>


                                            <address:addressFormSelector supportedCountries="${countries}"
                                                regions="${regions}" cancelUrl="${currentStepUrl}"
                                                country="${country}" />

                                                <div id="addressbook">

                                                    <spring:url var="selectDeliveryAddressUrl" value="{contextPath}/checkout/multi/delivery-address/select" htmlEscape="false">
                                                        <spring:param name="contextPath" value="${request.contextPath}" />
                                                    </spring:url>

                                                    <c:forEach items="${deliveryAddresses}" var="deliveryAddress" varStatus="status">
                                                        <div class="addressEntry">
                                                            <form action="${fn:escapeXml(selectDeliveryAddressUrl)}" method="GET">
                                                                <input type="hidden" name="selectedAddressCode" value="${fn:escapeXml(deliveryAddress.id)}" />
                                                                <ul>
                                                                    <li>
                                                                        <strong><c:if test="${ not empty deliveryAddress.title }"> ${fn:escapeXml(deliveryAddress.title)}&nbsp;</c:if>
                                                                        ${fn:escapeXml(deliveryAddress.firstName)}&nbsp;
                                                                        ${fn:escapeXml(deliveryAddress.lastName)}</strong>
                                                                        <br>
                                                                        ${fn:escapeXml(deliveryAddress.line1)}&nbsp;
                                                                        ${fn:escapeXml(deliveryAddress.line2)}
                                                                        <br>
                                                                        ${fn:escapeXml(deliveryAddress.town)}
                                                                        <c:if test="${not empty deliveryAddress.region.name}">
                                                                            &nbsp;${fn:escapeXml(deliveryAddress.region.name)}
                                                                        </c:if>
                                                                        <br>
                                                                        ${fn:escapeXml(deliveryAddress.country.name)}&nbsp;
                                                                        ${fn:escapeXml(deliveryAddress.postalCode)}
                                                                    </li>
                                                                </ul>
                                                                <button type="submit" class="btn btn-primary btn-block">
                                                                    <spring:theme code="checkout.multi.deliveryAddress.useThisAddress" />
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </c:forEach>
                                                </div>

                                                <address:suggestedAddresses selectedAddressUrl="/checkout/multi/delivery-address/select" />
                                    </div>

                                        <multi-checkout:pickupGroups cartData="${cartData}" />
                            </div>


                            <button id="addressSubmit" type="button"
                                class="checkout-next"><spring:theme code="checkout.multi.deliveryAddress.continue"/></button>
                        </ycommerce:testId>
                    </jsp:body>
                </multi-checkout:checkoutSteps>
            </div>

            <div class="col-lg-5 hidden-xs">
                <h3 class="subtitle-checkout"> Detail Pesanan </h3>
                <multi-checkout:checkoutOrderDetails cartData="${cartData}" showDeliveryAddress="false" showPaymentInfo="false" showTaxEstimate="false" showTax="true" />
            </div>

        </div>
    </div>
</div>

</template:page>
