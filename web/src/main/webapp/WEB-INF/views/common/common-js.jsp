<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/js/bootstrap/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery/jquery-3.1.1.min.js" var="jqueryJs" />
<spring:url value="/resources/js/common.js" var="commonJs" />

<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${commonJs}"></script>