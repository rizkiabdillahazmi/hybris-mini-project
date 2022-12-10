<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="variant-product-list" class="container-sm">

    <div class="row d-flex justify-content-center">
        <c:forEach items="${otherVariants}" var="product">
            <div class="col-md-4 d-flex justify-content-center mb-5">
                <div class="card catalog-product-block">
                    <a href="/demogroup3storefront/demoGroup3/en${product.pageUrl}">
                        <img class="card-img-top" src="${product.imgUrl}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title name">${product.name}</h5>
                            <c:catch var="exception">
                                <p class="card-text size">${product.ukuran}</p>
                            </c:catch>
                            <c:if test="${not empty exception}"></c:if>

                            <c:catch var="exception">
                                <p class="card-text size">${product.warna}</p>
                            </c:catch>
                            <c:if test="${not empty exception}"></c:if>

                            <p class="card-text size">${product.keterangan}</p>
                            <p class="variant-price"><format:price priceData="${product.priceData}"/></p>

                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="d-flex justify-content-center">
        <button class="button-5 btn--continue-shopping js-continue-shopping-button"
                data-continue-shopping-url="${fn:escapeXml(continueShoppingUrl)}">
            <spring:theme code="cart.page.continue"/>
        </button>
    </div>

</div>