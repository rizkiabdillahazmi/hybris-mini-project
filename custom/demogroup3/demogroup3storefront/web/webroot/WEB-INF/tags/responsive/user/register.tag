<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/login" var="loginUrl"/>
<c:url value="/" var="homeUrl"/>

<spring:url value="/login/register/termsandconditions" var="getTermsAndConditionsUrl"/>

<!-- begin:: Page -->
<div class="container login-page">
    <div class="card">
        <div class="card__logo">
            <a href="${fn:escapeXml(homeUrl)}">
                <img class="card__img" src="https://inthebox.net/images/logo-inthebox-440x80.png">
            </a>
        </div>

        <div>
            <h1 class="card__title">Daftar Sekarang</h1>
            <p class="card__subtitle">Masukkan detail Anda untuk menciptakan akun:</p>
        </div>

        <form:form method="post" modelAttribute="customRegisterForm" action="${action}" cssClass="card__form">
            <formElement:formSelectBoxDefaultEnabled idKey="register.title"
                labelKey="register.title" selectCSSClass="form-control hide"
                path="titleCode" mandatory="true" skipBlank="false"
                skipBlankMessageKey="form.select.none" items="${titles}" />

            <formElement:formInputBox idKey="register.id"
                labelKey="register.id" path="id" inputCSS="form-control" placeholder="ID"
                mandatory="true" />
            <formElement:formInputBox idKey="register.firstName"
                labelKey="register.firstName" path="firstName" inputCSS="form-control" placeholder="Nama Awal"
                mandatory="true" />
            <formElement:formInputBox idKey="register.lastName"
                labelKey="register.lastName" path="lastName" inputCSS="form-control" placeholder="Nama Akhir"
                mandatory="true" />
            <formElement:formInputBox idKey="register.email"
                labelKey="register.email" path="email" inputCSS="form-control" placeholder="Alamat Email"
                mandatory="true" />
            <formElement:formPasswordBox idKey="password" labelKey="register.pwd" placeholder="Kata Sandi"
                path="pwd" inputCSS="form-control" mandatory="true" />
            <formElement:formPasswordBox idKey="register.checkPwd" placeholder="Ulangi Kata Sandi"
                labelKey="register.checkPwd" path="checkPwd" inputCSS="form-control"
                mandatory="true" />

            <c:if test="${ not empty consentTemplateData }">
                <form:hidden path="consentForm.consentTemplateId" value="${consentTemplateData.id}" />
                <form:hidden path="consentForm.consentTemplateVersion" value="${consentTemplateData.version}" />
                <div class="checkbox">
                    <label class="control-label uncased">
                        <form:checkbox path="consentForm.consentGiven" disabled="true"/>
                        <c:out value="${consentTemplateData.description}" />

                    </label>
                </div>
                <div class="help-block">
                    <spring:theme code="registration.consent.link" />
                </div>

            </c:if>

            <spring:theme code="register.termsConditions" arguments="${getTermsAndConditionsUrl}" var="termsConditionsHtml" htmlEscape="false" />
            <template:errorSpanField path="termsCheck">
                <div class="checkbox">
                    <label class="control-label uncased">
                        <form:checkbox id="registerChkTermsConditions" path="termsCheck" disabled="true" checked="checked"/>
                        ${ycommerce:sanitizeHTML(termsConditionsHtml)}
                    </label>
                </div>
            </template:errorSpanField>

            <input type="hidden" id="recaptchaChallangeAnswered"
                value="${fn:escapeXml(requestScope.recaptchaChallangeAnswered)}" />
            <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
            <div class="form-actions clearfix">
                <ycommerce:testId code="register_Register_button">
                    <button class="card__form-button" type="submit">Daftar</button>
                </ycommerce:testId>
            </div>

        </form:form>

        <div class="card__register">
            <p class="card__register-msg">Sudah Memiliki Akun ? <a href="${fn:escapeXml(loginUrl)}" class="">Masuk</a></p>
        </div>
    </div>
</div>
<!-- end:: Page -->
