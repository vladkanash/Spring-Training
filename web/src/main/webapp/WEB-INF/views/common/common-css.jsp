<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/common.css" var="commonCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${commonCss}" rel="stylesheet" />
