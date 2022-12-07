<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <section class="testimony-page">
        <section class="testimony section">
            <div class="container">
                <div class="section-header">
                    <h1>TESTIMONI PEMBELI INTHEBOX</h1>
                </div>
                <div style="position: relative; top: -20px;">
                    Banyak pembeli yang merasa puas dengan kualitas kasur INTHEBOX
                </div>
                <div style="text-align: justify; margin-bottom: 25px;">
                    Kami menerima beragam komentar positif dari pembeli di seluruh Indonesia, bahkan dari kalangan artis. Bukan hanya pengalaman membeli kasur sping bed atau kasur busa, banyak juga yang merasa puas dengan produk INTHEBOX lainnya yang terdiri dari bantal, guling, dan sofa bed.<br><br>
                    Kami selalu berinovasi menghadirkan beragam jenis kasur dan produk penunjang istirahat lainnya. Kami paham bahwa istirahat yang cukup dan tidur berkualitas adalah hal yang penting. Oleh karena itu, kami selalu menggunakan material berkualitas premium, namun kamu bisa memilikinya dengan harga terjangkau.
                </div>
                <div class="row">
                    <c:forEach items="${allTestimony}" var="testimony" begin="${((paginationTestimony - 1) * 9)}" end="${(paginationTestimony * 9) - 1}">
                        <div class="col-md-4" style="margin-bottom: 20px;">
                            <div class="card testimony-card">
                                <div class="card-header d-flex align-items-center">
                                    <h2 class="name">${testimony.principal.name}</h2>
                                    <div class="ml-auto image">
                                        <picture>
                                            <source type="image/webp" data-srcset="https://inthebox.net/images/review-avatar.webp" srcset="https://inthebox.net/images/review-avatar.webp">
                                            <img src="https://inthebox.net/images/review-avatar.png" data-src="https://inthebox.net/images/review-avatar.png" class="pull-right lazyloaded" alt="Avatar">
                                        </picture>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="stars d-inline-flex">
                                        <c:forEach var="itemStar" begin="1" end="${testimony.rating.intValue()}">
                                            <div class="star">
                                                <picture>
                                                    <source type="image/webp" data-srcset="https://inthebox.net/images/star-full.webp" srcset="https://inthebox.net/images/star-full.webp">
                                                    <img src="https://inthebox.net/images/star-full.png" data-src="https://inthebox.net/images/star-full.png" class=" lazyloaded" alt="Star">
                                                </picture>
                                            </div>
                                        </c:forEach>
                                        <c:if test="${testimony.rating - testimony.rating.intValue() != 0}">
                                            <div class="star">
                                                <picture>
                                                    <source type="image/webp" data-srcset="https://inthebox.net/images/star-half.webp" srcset="https://inthebox.net/images/star-half.webp">
                                                    <img src="https://inthebox.net/images/star-half.png" data-src="https://inthebox.net/images/star-half.png" class=" lazyloaded" alt="Star">
                                                </picture>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="content">
                                        <div class="kt-margin-b-10">
                                            <strong>${testimony.headline}</strong>
                                        </div>
                                        ${testimony.comment}
                                    </div>
                                    <div class="datetime">
                                        <fmt:formatDate pattern="dd MMM yyyy - kk:mm a" value="${testimony.date}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="custom-pagination">
                    <c:if test="${paginationTestimony > 1}">
                        <a href="/demogroup3storefront/demoGroup3/en/testimoni?page=${paginationTestimony - 1}" class="page-link">< Halaman Sebelumnya</a>
                    </c:if>
                    <c:if test="${allTestimony.size() > 9 && paginationTestimony * 9 < allTestimony.size()}">
                        <a href="/demogroup3storefront/demoGroup3/en/testimoni?page=${paginationTestimony + 1}" class="page-link">Halaman Berikutnya ></a>
                    </c:if>
                </div>
            </div>
        </section>
    </section>
</div>