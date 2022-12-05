<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="isForceInStock" value="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}"/>
<c:choose> 
  <c:when test="${isForceInStock}">
    <c:set var="maxQty" value="FORCE_IN_STOCK"/>
  </c:when>
  <c:otherwise>
    <c:set var="maxQty" value="${product.stock.stockLevel}"/>
  </c:otherwise>
</c:choose>

<c:set var="qtyMinus" value="1" />

<div class="addtocart-component">
		<c:if test="${empty showAddToCart ? true : showAddToCart}">
		<div class="qty-selector input-group js-qty-selector">
		    <div class="input-group bootstrap-touchspin bootstrap-touchspin-injected">
              <span class="input-group-btn input-group-prepend">
                <button class="btn btn-block spinner-button down bootstrap-touchspin-down js-qty-selector-minus button-quantity-product" type="button" <c:if test="${qtyMinus <= 1}"><c:out value="disabled='disabled'"/></c:if> ><span class="palmer-lake-icon palmer-lake-minus" aria-hidden="true" ></span></button>
              </span>

              <!-- <input type="text" id="quantity" class="form-control func_touchspin" style="text-align: center" value="1" name="quantity" placeholder="" data-min="1" data-max="100" data-stepinterval="1" data-prefix="" data-postfix="" > -->
              <input type="text" maxlength="3" class="form-control func_touchspin js-qty-selector-input quantity-product-form-control" size="1" value="${fn:escapeXml(qtyMinus)}"
               					   data-max="${fn:escapeXml(maxQty)}" data-min="1" name="pdpAddtoCartInput"  id="pdpAddtoCartInput"
               					   data-buttondown_class="btn btn-block spinner-button down" data-buttonup_class="btn btn-block spinner-button up" />

              <span class="input-group-btn input-group-append">
                <button class="btn btn-block spinner-button up bootstrap-touchspin-up js-qty-selector-plus button-quantity-product" type="button"><span class="palmer-lake-icon palmer-lake-plus" aria-hidden="true"></span></button>
              </span>
            </div>


		</div>
		</c:if>
		<c:if test="${product.stock.stockLevel gt 0}">
			<c:set var="productStockLevelHtml">${fn:escapeXml(product.stock.stockLevel)}&nbsp;
				<spring:theme code="product.variants.in.stock"/>
			</c:set>
		</c:if>
		<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
			<c:set var="productStockLevelHtml">
				<spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/>
			</c:set>
		</c:if>
		<c:if test="${isForceInStock}">
			<c:set var="productStockLevelHtml">
				<spring:theme code="product.variants.available"/>
			</c:set>
		</c:if>
		<div class="stock-wrapper clearfix">
		    <div>${productStockLevelHtml}</div>
		</div>
		 <div class="actions">
        <c:if test="${multiDimensionalProduct}" >
                <c:url value="${product.url}/orderForm" var="productOrderFormUrl"/>
                <a href="${productOrderFormUrl}" class="btn btn-default btn-block btn-icon js-add-to-cart glyphicon-list-alt">
                    <spring:theme code="order.form" />
                </a>
        </c:if>
        <action:actions element="div"  parentComponent="${component}"/>
    </div>
</div>
