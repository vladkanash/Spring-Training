<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top" >
    <div class="navbar-header">
        <a class="navbar-brand" href="<spring:url value="/productList"/>">
            <spring:message code="navbar.title"/>
        </a>
    </div>
    <c:if test="${param.showLogin eq true}">
        <sec:authorize access="isAnonymous()" >
            <div class="pull-right nav-buttons">
                <a href="<spring:url value="/login"/>" class="btn btn-primary navbar-btn">
                    <spring:message code="navbar.button.login"/>
                </a>
                <a href="<spring:url value="/register"/>" class="btn btn-success navbar-btn">
                    <spring:message code="navbar.button.register"/>
                </a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout" />
            <form action="${logoutUrl}" id="logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            <sec:authentication property="principal.username" var="username"/>
            <div class="pull-right nav-buttons">
                <p class="navbar-text">
                    <spring:message code="navbar.message.welcome"/>&nbsp;${username}
                </p>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="<spring:url value="/admin"/>" class="btn btn-success navbar-btn">
                        <spring:message code="navbar.button.admin"/>
                    </a>
                </sec:authorize>
                <a href="<spring:url value="/user"/>" class="btn btn-primary navbar-btn">
                    <spring:message code="navbar.button.profile"/>
                </a>
                <button class="btn btn-primary navbar-btn" onclick="$('#logout').submit()">
                    <spring:message code="navbar.button.logout"/>
                </button>
            </div>
        </sec:authorize>
    </c:if>
</nav>