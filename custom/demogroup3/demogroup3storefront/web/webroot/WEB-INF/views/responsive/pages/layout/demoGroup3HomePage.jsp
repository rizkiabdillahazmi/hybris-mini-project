<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:url value="/testimony" var="testimoniUrl"/>
<c:url value="/c/IntheBox" var="productUrl"/>

<template:page pageTitle="${pageTitle}">
    <%--<cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>

    <cms:pageSlot position="Section2" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>--%>

    <section class="section section--banner">
            <div class="carousel-banner owl-carousel owl-theme owl-loaded">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                       <cms:pageSlot position="Section1" var="feature">
                               <cms:component component="${feature}" />
                           </cms:pageSlot>
                    </div>
                </div>
                <!-- <div class="owl-nav">
                    <div class="owl-prev">prev</div>
                    <div class="owl-next">next</div>
                </div> -->
                <!-- <div class="owl-dots">
                    <div class="owl-dot active"><span></span></div>
                    <div class="owl-dot"><span></span></div>
                    <div class="owl-dot"><span></span></div>
                </div> -->
            </div>
        </section>

        <section class="section section--product">
            <div class="product__title">
                <h1 class="product__title">BELI <em>KASUR</em> INTHEBOX</h1>
            </div>
            <div class="product__desc">
                <p style="margin-bottom: 1rem;">Tersedia beragam jenis kasur INTHEBOX dengan kualitas premium.</p>
                <p>Pilih spring bed berkualitas dengan ukuran kasur sesuai kebutuhan.</p>
            </div>
            <div class="container-home product__content">
                <cms:pageSlot position="Section2" var="feature">
                        <cms:component component="${feature}" />
                    </cms:pageSlot>
            </div>

        </section>

        <section class="section section--unboxing">
            <div class="container-home">
                <div class="unboxing__title">
                    <h1 class="unboxing__title">UNBOXING INTHEBOX</h1>
                </div>
                <div class="unboxing__desc">
                    <div class="card-unboxing">
                        <img src="https://inthebox.net/images/layout-v2/experience_1.png" alt="" class="card-unboxing__img">
                        <h2 class="card-unboxing__title">Keluarkan</h2>
                        <div class="card-unboxing__desc">Keluarkan kasur INTHEBOX dari kemasannya secara perlahan-lahan
                        </div>
                    </div>
                    <div class="card-unboxing">
                        <img src="https://inthebox.net/images/layout-v2/experience_2.png" alt="" class="card-unboxing__img">
                        <h2 class="card-unboxing__title">Posisikan</h2>
                        <div class="card-unboxing__desc">Keluarkan kasur INTHEBOX dari kemasannya secara perlahan-lahan
                        </div>
                    </div>
                    <div class="card-unboxing">
                        <img src="https://inthebox.net/images/layout-v2/experience_3.png" alt="" class="card-unboxing__img">
                        <h2 class="card-unboxing__title">Buka Plastik</h2>
                        <div class="card-unboxing__desc">Lepaskan plastik pembungkus dan pastikan logo INTHEBOX ada di bawah
                        </div>
                    </div>
                    <div class="card-unboxing">
                        <img src="https://inthebox.net/images/layout-v2/experience_4.png" alt="" class="card-unboxing__img">
                        <h2 class="card-unboxing__title">Tunggu Mengembang</h2>
                        <div class="card-unboxing__desc">Kasur INTHEBOX akan langsung mengembang. Tunggu 60 menit untuk
                            digunakan.
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section section--testimony">
            <div class="container-home">
                <h1 class="testimony__title">TESTIMONI</h1>
                <div class="testimony__desc">
                    <p style="margin-bottom: 0.5rem;">Banyak pembeli yang merasa puas dengan kualitas kasur INTHEBOX</p>
                    <p>Kami menerima beragam komentar positif dari pembeli di seluruh Indonesia, bahkan dari kalangan artis.
                        Bukan hanya pengalaman membeli kasur sping bed atau kasur busa, banyak juga yang merasa puas dengan
                        produk INTHEBOX lainnya yang terdiri dari bantal, guling, dan sofa bed.
                    </p>
                </div>

                <div class="testimony__stars">
                    <div class="stars">
                        <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                        <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                        <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                        <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                        <img class="star" src="https://inthebox.net/images/star-half.webp" alt="Star">
                    </div>
                    <span class="testimony__stars-desc">4.9/5 (268 Reviews)</span>
                </div>

                <div class="testimony__content">
                    <div class="testimony-card">
                        <div class="testimony-card__title">
                            <div class="name">Baim Wong</div>
                            <img src="https://inthebox.net/images/review-avatar.webp" alt="Avatar">
                        </div>
                        <div class="testimony-card__body">
                            <div class="stars">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                            </div>

                            <div class="testimony-card__body-title">Mantap Banget</div>
                            <div class="testimony-card__body-desc">Kasur baru yang packing nya simple banget, masuk ke dalam
                                box! Kasur @inthebox.id dengan teknologi Pocket Springbed ini enak banget, nyaman dan empuk!
                                Ada Free Bantal dan Free Ongkirnya juga lhoo! Mantap banget
                            </div>
                            <div class="testimony-card__body-date">9 Sep 2020 - 6:22 pm</div>
                        </div>
                    </div>

                    <div class="testimony-card">
                        <div class="testimony-card__title">
                            <div class="name">Raffi Ahmad</div>
                            <img src="https://inthebox.net/images/review-avatar.webp" alt="Avatar">
                        </div>
                        <div class="testimony-card__body">
                            <div class="stars">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                            </div>

                            <div class="testimony-card__body-title">Pilih kasur INTHEBOX aja!</div>
                            <div class="testimony-card__body-desc">
                                Pokoknya kalau mau beli spring bed yang bagus, materialnya berkualitas, nyaman, dan harganya
                                murah, langsung pilih kasur INTHEBOX aja. Apalagi INTHEBOX+ ini nih bagus banget. Selama gue
                                pakai kasur INTHEBOX di rumah itu bikin tidur jadi nyenyak, pas bangun langsung segar
                                badannya.
                            </div>
                            <div class="testimony-card__body-date">9 Sep 2020 - 6:22 pm</div>
                        </div>
                    </div>

                    <div class="testimony-card">
                        <div class="testimony-card__title">
                            <div class="name">Melaney Ricardo</div>
                            <img src="https://inthebox.net/images/review-avatar.webp" alt="Avatar">
                        </div>
                        <div class="testimony-card__body">
                            <div class="stars">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                                <img class="star" src="https://inthebox.net/images/star-full.webp" alt="Star">
                            </div>

                            <div class="testimony-card__body-title">Terima kasih Inthebox!</div>
                            <div class="testimony-card__body-desc">
                                Produk memuaskaan sekalii.. dan seru waktu unboxing ya :-D Nyaman sekalii. Pas dapet harga flash sale lagi. Mantap banget sihh klo ini Semoga aweet ya. Trimakasih Inthebox. Trimakasih
                                Tokopedia.
                            </div>
                            <div class="testimony-card__body-date">9 Sep 2020 - 6:22 pm</div>
                        </div>
                    </div>
                </div>

                <div class="testimony__all">
                    <a class="btn-testimony" href="${fn:escapeXml(testimoniUrl)}">
                        Lihat Semua Testimoni
                    </a>
                </div>
            </div>
        </section>

</template:page>
