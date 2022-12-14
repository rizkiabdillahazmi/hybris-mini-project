<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>

<c:url value="/cart" var="cartUrl"/>
<c:url value="/" var="homeUrl"/>
<c:url value="/IntheBox/c/IntheBox" var="productUrl"/>
<c:url value="/about" var="aboutUrl"/>
<c:url value="/testimony" var="testimoniUrl"/>
<spring:htmlEscape defaultHtmlEscape="true" />


<!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="style.css" />
    <link href='https://fonts.googleapis.com/css?family=Nunito' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.cdnfonts.com/css/brown" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">


<div class="desktop-view">
   <div class="upper">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="d-flex flex-row">
                    <div class="upper-img">
                        <img src="https://inthebox.net/images/layout-v2/header-icon-10y-warranty.png" alt="" class="upper-icon-img">
                        <p class="upper-text">Garansi 10 Tahun</p>
                    </div>
                    <div class="upper-img">
                        <img src="https://inthebox.net/images/layout-v2/header-icon-free-shipping.png" alt="" class="upper-icon-img">
                        <p class="upper-text">Free Shipping pulau Jawa & Sumatra</p>
                    </div>
                </div>
                <div class="d-flex flex-row">
                    <ul class="upper-link">
                        <li><a href="">Garansi</a></li>
                        <li><a href="">Konfirmasi</a></li>
                        <li><a href="">Cek Resi</a></li>
                    </ul>
                </div>
              </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light navbar-new">
        <div class="container">
            <div class="navbar-brand">
            <cms:pageSlot position="SiteLogo" var="logo" limit="1">
               <cms:component component="${logo}" element="div" class="yComponentWrapper logo"/>
            </cms:pageSlot>
            </div>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 nav-link-head">
              <cms:pageSlot position="NavigationBar" var="component">
                  <cms:component component="${component}" />
              </cms:pageSlot>
            </ul>
            <span class="navbar-right d-flex">
                 <div class="login nav-item">
                     <ul class="login-link">
                        <c:if test="${empty hideHeaderLinks}">
                           <c:if test="${uiExperienceOverride}">
                                <li class="backToMobileLink">
                                    <c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl" />
                                    <a href="${fn:escapeXml(backToMobileStoreUrl)}">
                                        <spring:theme code="text.backToMobileStore" />
                                    </a>
                                </li>
                            </c:if>

                            <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                                <c:set var="maxNumberChars" value="25" />
                                    <c:if test="${fn:length(user.firstName) gt maxNumberChars}">
                                        <c:set target="${user}" property="firstName"
                                        value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
                                    </c:if>
                                    <li class="logged_in js-logged_in">
                                        <ycommerce:testId code="header_LoggedUser">
                                            <spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" />
                                        </ycommerce:testId>
                                    </li>
                            </sec:authorize>


                            <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')" >
                                <li class="liOffcanvas">
                                    <ycommerce:testId code="header_Login_link">
                                        <c:url value="/login" var="loginUrl" />
                                        <a href="${fn:escapeXml(loginUrl)}">
                                            LOGIN
                                        </a>
                                    </ycommerce:testId>
                                </li>
                            </sec:authorize>

                            <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')" >
                                <li class="liOffcanvas">
                                    <ycommerce:testId code="header_signOut">
                                        <c:url value="/logout" var="logoutUrl"/>
                                        <a href="${fn:escapeXml(logoutUrl)}">
                                            <spring:theme code="header.link.logout" />
                                        </a>
                                    </ycommerce:testId>
                                </li>
                            </sec:authorize>
                        </c:if>
                        </ul>
                 </div>
                 <a href="${fn:escapeXml(cartUrl)}"><img src="https://inthebox.net/images/layout-v2/cart-icon.png" alt="" class="cart-img"></a>
            </span>
          </div>
        </div>
    </nav>
</div>

<div class="hp-view">
       <div class="upper">
            <div class="container">
                <div class="d-flex justify-content-between upper-hp">
                    <div class="d-flex flex-row">
                        <div class="upper-img">
                            <img src="https://inthebox.net/images/layout-v2/header-icon-10y-warranty.png" alt="" class="upper-icon-img">
                            <p class="upper-text">Garansi 10 Tahun</p>
                        </div>
                        <div class="upper-img">
                            <img src="https://inthebox.net/images/layout-v2/header-icon-free-shipping.png" alt="" class="upper-icon-img">
                            <p class="upper-text">Free Shipping pulau Jawa & Sumatra</p>
                        </div>
                    </div>
                  </div>
            </div>
        </div>

        <nav class="navbar-expand-lg navbar-light navbar-hp">
          <div class="container-fluid">
             <div class="navbar-brand">
                <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                    <cms:component component="${logo}" element="div" class="yComponentWrapper logo-hp"/>
                </cms:pageSlot>
             </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0 nav-list-hp">
                <li class="nav-item mx-auto">
                  <a class="nav-list-item first-list-item" href="${fn:escapeXml(homeUrl)}">Home</a>
                </li>
                <li class="nav-item mx-auto">
                  <a class="nav-list-item" href="${fn:escapeXml(productUrl)}">Produk</a>
                </li>
                <li class="nav-item mx-auto">
                  <a class="nav-list-item" href="${fn:escapeXml(aboutUrl)}">Tentang Kami</a>
                </li>
                <li class="nav-item mx-auto">
                    <a class="nav-list-item" href="${fn:escapeXml(testimoniUrl)}">Testimoni</a>
                <li class="nav-item mx-auto">
                    <a class="nav-list-item" href="${fn:escapeXml(cartUrl)}">Keranjang</a>
                </li>
                <li class="nav-item login-item-hp">
                    <c:if test="${empty hideHeaderLinks}">
                        <c:if test="${uiExperienceOverride}">
                            <li class="backToMobileLink">
                                <c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl" />
                                <a href="${fn:escapeXml(backToMobileStoreUrl)}">
                                    <spring:theme code="text.backToMobileStore" />
                                </a>
                            </li>
                        </c:if>

                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                            <c:set var="maxNumberChars" value="25" />
                            <c:if test="${fn:length(user.firstName) gt maxNumberChars}">
                                <c:set target="${user}" property="firstName"
                                value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
                            </c:if>
                        </sec:authorize>

                        <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')" >
                            <li class="liOffcanvas mx-auto">
                                <ycommerce:testId code="header_Login_link">
                                    <c:url value="/login" var="loginUrl" />
                                    <a href="${fn:escapeXml(loginUrl)}">
                                        Login
                                    </a>
                               </ycommerce:testId>
                            </li>
                        </sec:authorize>

                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')" >
                            <li class="liOffcanvas mx-auto">
                                <ycommerce:testId code="header_signOut">
                                    <c:url value="/logout" var="logoutUrl"/>
                                    <a href="${fn:escapeXml(logoutUrl)}">
                                        <spring:theme code="header.link.logout" />
                                    </a>
                                </ycommerce:testId>
                            </li>
                        </sec:authorize>
                    </c:if>
                </li>
                <div class="sticker">
                    <img src="https://www.line-stickers.com/wp-content/uploads/2019/04/Shibasays-The-Sleeping-Prince-.png" class="sticker-sleep">
                </div>
            </div>
          </div>
        </nav>
</div>

<div class="tablet-view">
         <div class="upper">
            <div class="container">
                <div class="d-flex justify-content-between upper-hp">
                    <div class="d-flex flex-row">
                        <div class="upper-img">
                            <img src="https://inthebox.net/images/layout-v2/header-icon-10y-warranty.png" alt="" class="upper-icon-img">
                            <p class="upper-text">Garansi 10 Tahun</p>
                        </div>
                        <div class="upper-img">
                            <img src="https://inthebox.net/images/layout-v2/header-icon-free-shipping.png" alt="" class="upper-icon-img">
                            <p class="upper-text">Free Shipping pulau Jawa & Sumatra</p>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    <div class="navbar-tablet">
      <div class="container">
        <div class="navbar-tablet-content">
          <div class="navbar-logo-tablet">
            <img
              src="https://inthebox.net/images/layout-v2/header-logo-white.png"
              class="logo"
            />
          </div>
          <div class="navbar-menu-tablet">
            <ul class="nav-link">
              <li><a href="${fn:escapeXml(homeUrl)}">Home</a></li>
              <li><a href="${fn:escapeXml(productUrl)}">Produk</a></li>
              <li><a href="${fn:escapeXml(aboutUrl)}">Tentang Kami</a></li>
              <li><a href="${fn:escapeXml(testimoniUrl)}">Testimoni</a></li>
              <li><a href="${fn:escapeXml(cartUrl)}">Keranjang</a></li>
              <li class="nav-item login-item-hp">
                    <c:if test="${empty hideHeaderLinks}">
                        <c:if test="${uiExperienceOverride}">
                            <li class="backToMobileLink">
                                <c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl" />
                                <a href="${fn:escapeXml(backToMobileStoreUrl)}">
                                    <spring:theme code="text.backToMobileStore" />
                                </a>
                            </li>
                        </c:if>

                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                            <c:set var="maxNumberChars" value="25" />
                            <c:if test="${fn:length(user.firstName) gt maxNumberChars}">
                                <c:set target="${user}" property="firstName"
                                value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
                            </c:if>
                        </sec:authorize>

                        <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')" >
                            <li class="liOffcanvas mx-auto">
                                <ycommerce:testId code="header_Login_link">
                                    <c:url value="/login" var="loginUrl" />
                                    <a href="${fn:escapeXml(loginUrl)}">
                                        Login
                                    </a>
                               </ycommerce:testId>
                            </li>
                        </sec:authorize>

                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')" >
                            <li class="liOffcanvas mx-auto">
                                <ycommerce:testId code="header_signOut">
                                    <c:url value="/logout" var="logoutUrl"/>
                                    <a href="${fn:escapeXml(logoutUrl)}">
                                        <spring:theme code="header.link.logout" />
                                    </a>
                                </ycommerce:testId>
                            </li>
                        </sec:authorize>
                    </c:if>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

</div>


