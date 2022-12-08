<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:url value="/register" var="registerUrl"/>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="hideDescription" value="checkout.login.loginAndCheckout" />


<!-- begin:: Page -->
<div class="container login-page">
    <div class="card">
        <div class="card__logo">
            <a href="card__logo-link">
                <img class="card__img" src="https://inthebox.net/images/logo-inthebox-440x80.png">
            </a>
        </div>

        <div class="card__title">
            <h1>Masuk</h1>
        </div>

        <form:form action="${action}" method="post" modelAttribute="loginForm" cssClass="card__form">
            <c:if test="${not empty message}">
                <span class="has-error"> <spring:theme code="${message}" />
                </span>
            </c:if>

                <formElement:formInputBox idKey="j_username" labelKey="login.email" placeholder="Alamat Email"
                    path="j_username" mandatory="true" />
                <formElement:formPasswordBox idKey="j_password" placeholder="Kata Sandi"
                    labelKey="login.password" path="j_password" inputCSS="form-control"
                    mandatory="true" />
                <ycommerce:testId code="loginAndCheckoutButton">
                    <button class="card__form-button" type="submit">Masuk</button>
                </ycommerce:testId>


            <c:if test="${expressCheckoutAllowed}">
                <button type="submit" class="btn btn-default btn-block expressCheckoutButton"><spring:theme code="text.expresscheckout.header" /></button>
                <input id="expressCheckoutCheckbox" name="expressCheckoutEnabled" type="checkbox" class="form left doExpressCheckout display-none" />
            </c:if>

            <div class="mx-auto mt-4 card__register">
                <p class="text-center card__register-msg">Belum Memiliki Akun ? <a href="${fn:escapeXml(registerUrl)}" class="" >Daftar Sekarang</a></p>
            </div>
        </form:form>
    </div>
</div>
<!-- end:: Page -->



