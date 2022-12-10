<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="variant-product-list" class="container-sm mt-5">

    <div class="row d-flex justify-content-center">
        <c:forEach items="${otherVariants}" var="product">
            <div class="col-md-4 d-flex justify-content-center mb-5">
                <div class="card">
                    <a href="/demogroup3storefront/demoGroup3/en${product.pageUrl}">
                        <img class="card-img-top" src="${product.imgUrl}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">${product.keterangan}</p>

                            <c:catch var="exception">${product.ukuran}</c:catch>
                            <c:if test="${not empty exception}"></c:if>

                            <c:catch var="exception">${product.warna}</c:catch>
                            <c:if test="${not empty exception}"></c:if>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

</div>