<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />
<spring:url value="/cart/export" var="exportUrl" htmlEscape="false"/>

<c:choose>
    <c:when test="${not empty cartData.rootGroups}">
        <c:url value="/cart/checkout" var="checkoutUrl" scope="session"/>
        <c:url value="/quote/create" var="createQuoteUrl" scope="session"/>
        <c:url value="${continueUrl}" var="continueShoppingUrl" scope="session"/>
        <c:set var="showTax" value="false"/>

        <div id="custom-cart-main" class="container">
            <div class="cart-header border-bottom">
                <div class="row">
                    <div class="col-xs-12 col-sm-5">
                        <h1 class="cart-headline">
                            <spring:theme code="text.cart"/>
                            <c:if test="${not empty cartData.code}">
                                <span class="cart__id--label">
                                    <spring:theme code="basket.page.cartIdShort"/><span class="cart__id">${fn:escapeXml(cartData.code)}</span>
                                </span>
                            </c:if>
                        </h1>
                    </div>
                    <div class="col-xs-12 col-sm-7">

                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                            <c:if test="${not empty savedCartCount and savedCartCount ne 0}">
                                <spring:url value="/my-account/saved-carts" var="listSavedCartUrl" htmlEscape="false"/>
                                <a href="${fn:escapeXml(listSavedCartUrl)}" class="save__cart--link cart__head--link">
                                    <spring:theme code="saved.cart.total.number" arguments="${savedCartCount}"/>
                                </a>
                                <c:if test="${not empty quoteCount and quoteCount ne 0}">
                                    <spring:url value="/my-account/my-quotes" var="listQuotesUrl" htmlEscape="false"/>
                                    <a href="${fn:escapeXml(listQuotesUrl)}" class="cart__quotes--link cart__head--link">
                                        <spring:theme code="saved.quote.total.number" arguments="${quoteCount}"/>
                                    </a>
                                </c:if>

                            </c:if>
                        </sec:authorize>
                        <div class="col-xs-12 col-sm-8"></div>
                        <div class="col-xs-12 col-sm-4 d-flex justify-content-end">
                            <cart:saveCart/>
                            <a href="${exportUrl}" class="custom-export__cart--link">
                                <spring:theme code="basket.export.csv.file" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row d-none">
                <div class="col-xs-12 pull-right cart-actions--print">
                    <div class="cart__actions border">
                        <div class="row">
                            <div class="col-sm-4 col-md-3 pull-right">
                                <ycommerce:testId code="checkoutButton">
                                    <button class="btn btn-primary btn-block btn--continue-checkout js-continue-checkout-button" data-checkout-url="${fn:escapeXml(checkoutUrl)}">
                                        <spring:theme code="checkout.checkout"/>
                                    </button>
                                </ycommerce:testId>
                            </div>

                            <c:if test="${not empty siteQuoteEnabled and siteQuoteEnabled eq 'true'}">
                                <div class="col-sm-4 col-md-3 col-md-offset-3 pull-right">
                                    <button class="btn btn-default btn-block btn-create-quote js-create-quote-button" data-create-quote-url="${fn:escapeXml(createQuoteUrl)}">
                                        <spring:theme code="quote.create"/>
                                    </button>
                                </div>
                            </c:if>

                            <div class="col-sm-4 col-md-3 pull-right">
                                <button class="btn btn-default btn-block btn--continue-shopping js-continue-shopping-button" data-continue-shopping-url="${fn:escapeXml(continueShoppingUrl)}">
                                    <spring:theme code="cart.page.continue"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="cart-section-header text-center mt-5">
                    <h1>Keranjang Belanja</h1>
                </div>

                <div class="">
                    <div class="js-cart-top-totals cart__top--totals">
                        <c:choose>
                            <c:when test="${fn:length(cartData.entries) > 1 or fn:length(cartData.entries) == 0}">
                                <spring:theme code="basket.page.totals.total.items" arguments="${fn:length(cartData.entries)}"/>
                            </c:when>
                            <c:otherwise>
                                <spring:theme code="basket.page.totals.total.items.one" arguments="${fn:length(cartData.entries)}"/>
                            </c:otherwise>
                        </c:choose>
                        <ycommerce:testId code="cart_totalPrice_label">
                        <span class="cart__top--amount">
                            <c:choose>
                                <c:when test="${showTax}">
                                    <format:price priceData="${cartData.totalPriceWithTax}"/>
                                </c:when>
                                <c:otherwise>
                                    <format:price priceData="${cartData.totalPrice}"/>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        </ycommerce:testId>
                    </div>
                </div>
            </div>



            <cart:cartItems cartData="${cartData}"/>

            <div class="row d-none">
                <cart:exportCart/>
            </div>
        </div>
    </c:when>


    <c:otherwise>
        <section class="cart-page">
          <section class="cart-section">
            <div class="container">
              <div class="cart-section-header text-center">
                <h1>Keranjang Belanja</h1>
              </div>
              <div class="text-center my-5">
                <h3>Belum ada barang dalam keranjang belanja Anda.</h3> Lihat <a href="${fn:escapeXml(continueShoppingUrl)}">Produk</a> untuk mulai berbelanja.
              </div>
            </div>
          </section>
          <!-- Cart Section -->
        </section>
    </c:otherwise>
</c:choose>

<cart:ajaxCartTopTotalSection/>
