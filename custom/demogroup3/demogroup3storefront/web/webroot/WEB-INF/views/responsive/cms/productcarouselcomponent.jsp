<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>


<spring:htmlEscape defaultHtmlEscape="true" />

<c:choose>
    <c:when test="${not empty productData}">
        <div class="carousel__component">
            <div class="carousel__component--headline">${fn:escapeXml(title)}</div>

            <c:choose>
                <c:when test="${component.popup}">
                    <div class="carousel-product owl-carousel owl-theme owl-loaded">
                        <!-- <div id="quickViewTitle" class="quickView-header display-none">
                            <div class="headline">
                                <span class="headline-text">
                                    <spring:theme code="popup.quick.view.select" />
                                </span>
                            </div>
                        </div> -->
                        <div class="owl-stage-outer">
                            <div class="owl-stage">
                                <c:forEach items="${productData}" var="product">
                                    <c:url value="${product.url}/quickView" var="productQuickViewUrl" />
                                    <div class="owl-item">
                                        <div class="product__content-container slide-item">
                                            <div class="card-product">
                                                                                                <div class="carousel__item--thumb">
                                                                                                    <product:productPrimaryReferenceImage product="${product}"
                                                                                                        format="product" />
                                                                                                </div>
                                                                                                <h2 class="card-product__title">${fn:escapeXml(product.name)}</h2>
                                                                                                <div class="card-product__desc description">${ycommerce:sanitizeHTML(product.description)}</div>
                                                                                                <div class="card-product__price"><format:fromPrice priceData="${product.price}" /></div>
                                                                                                <a href="${productUrl}" class="card-product__button">Beli Sekarang</a>

                                                                                        </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="carousel-product owl-carousel owl-theme owl-loaded">
                        <div class="owl-stage-outer">
                            <div class="owl-stage">
                                <c:forEach items="${productData}" var="product">
                                    <c:url value="${product.url}" var="productUrl" />
                                    <div class="owl-item">
                                        <div class="product__content-container slide-item">
                                            <div class="card-product">
                                                    <div class="carousel__item--thumb">
                                                        <product:productPrimaryReferenceImage product="${product}"
                                                            format="product" />
                                                    </div>
                                                    <h2 class="card-product__title">${fn:escapeXml(product.name)}</h2>
                                                    <div class="card-product__desc description">${ycommerce:sanitizeHTML(product.description)}</div>
                                                    <div class="card-product__price"><format:fromPrice priceData="${product.price}" /></div>
                                                    <a href="${productUrl}" class="card-product__button">Beli Sekarang</a>

                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:when>

    <c:otherwise>
        <component:emptyComponent />
    </c:otherwise>
</c:choose>