<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/cart" var="cartUrl"/>
<c:url value="/" var="homeUrl"/>
<c:url value="/IntheBox/c/IntheBox" var="productUrl"/>
<c:url value="/about" var="aboutUrl"/>
<c:url value="/testimoni" var="testimoniUrl"/>
<spring:htmlEscape defaultHtmlEscape="true" />

<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss" />
	</jsp:attribute>

	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts" />
	</jsp:attribute>

	<jsp:body>
		<div class="branding-mobile hidden-md hidden-lg">
			<div class="js-mobile-logo">
				<%--populated by JS acc.navigation--%>
			</div>
		</div>
		<main data-currency-iso-code="${fn:escapeXml(currentCurrency.isocode)}">
			<spring:theme code="text.skipToContent" var="skipToContent" />
			<a href="#skip-to-content" class="skiptocontent" data-role="none">${fn:escapeXml(skipToContent)}</a>
			<spring:theme code="text.skipToNavigation" var="skipToNavigation" />
			<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${fn:escapeXml(skipToNavigation)}</a>


			<header:header hideHeaderLinks="${hideHeaderLinks}" />


			
			
			<a id="skip-to-content"></a>
		
			<div class="main__inner-wrapper">
				<common:globalMessages />
				<cart:cartRestoration />
				<jsp:doBody />
			</div>


          <!--Footer-->
            <div class="footer-new">
                <div class="container">
                    <div class="row footer-component">
                        <div class="col-md-6">
                            <img src="https://inthebox.net/images/layout-v2/header-logo-white.png" alt="" class="logo">
                            <div class="contact">
                                <input type="text" class="contact-input" placeholder="Masukkan Alamat Email">
                                <button class="contact-button">Tambah</button>
                                <div class="footer-icon">
                                    <i class="fa fa-facebook footer-icon-fa" aria-hidden="true"></i>
                                    <i class="fa fa-instagram footer-icon-fa" aria-hidden="true"></i>
                                    <i class="fa fa-envelope footer-icon-fa" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p class="footer-sub-title">PRODUK</p>
                            <p class="footer-menu-space"><a href="${fn:escapeXml(productUrl)}" class="footer-list-menu">Produk</a></p>
                        </div>
                        <div class="col-md-2">
                            <p class="footer-sub-title">SUPPORT</p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Aktivasi gerai</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Cek Resi</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">FAQ</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Bantuan</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Syarat & Ketentuan</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Kebijakan Privasi</a></p>
                        </div>
                        <div class="col-md-2">
                            <p class="footer-sub-title">ABOUT</p>
                            <p class="footer-menu-space"><a href="${fn:escapeXml(aboutUrl)}" class="footer-list-menu">Tentang Kami</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Toko Offline</a></p>
                            <p class="footer-menu-space"><a href="" class="footer-list-menu">Blog</a></p>
                            <p class="footer-menu-space"><a href="${fn:escapeXml(testimoniUrl)}" class="footer-list-menu">Testimoni</a></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright">
                <div class="container">
                    <div class="row copyright-items">
                        <div class="col-md-2">
                            <p><i class="fa fa-phone" aria-hidden="true"></i> (021) 30306286</p>
                        </div>
                        <div class="col-md-2">
                            <p><i class="fa fa-commenting" aria-hidden="true"></i>+62 819 234 659</p>
                        </div>
                        <div class="col-md-5"></div>
                        <div class="col-md-3">
                            Copyright &copy; 2019-2022
                        </div>
                    </div>
                </div>
            </div>

		    <script
              src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
              integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
              crossorigin="anonymous"
            ></script>

		</main>

	</jsp:body>

</template:master>
