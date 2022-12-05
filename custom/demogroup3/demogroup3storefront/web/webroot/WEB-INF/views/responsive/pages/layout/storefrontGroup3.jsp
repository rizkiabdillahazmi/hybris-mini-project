<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:page pageTitle="${pageTitle}">

<div class="price_engine_page">
   <div>
       <div class="main-section">
           <cms:pageSlot position="Section1" var="feature">
               <cms:component component="${feature}" element="div" class="main-content"/>
           </cms:pageSlot>
       </div>
       <div class="main-section">
           <cms:pageSlot position="Section2" var="feature">
               <cms:component component="${feature}" element="div" class="main-content"/>
           </cms:pageSlot>
       </div>

   </div>
</div>
</template:page>

