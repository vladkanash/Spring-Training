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
                <a href="<spring:url value="/login"/>" class="btn btn-success navbar-btn">Login</a>
                <a href="<spring:url value="/register"/>" class="btn btn-primary navbar-btn">Register</a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout" />
            <form action="${logoutUrl}" id="logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>

            <div class="pull-right nav-buttons">
                <p class="navbar-text">
                    Welcome, <sec:authentication property="principal.username"/>
                </p>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="<spring:url value="/admin"/>" class="btn btn-success navbar-btn">To the admin page</a>
                </sec:authorize>
                <button class="btn btn-primary navbar-btn" onclick="$('#logout').submit()">Logout</button>
            </div>
        </sec:authorize>
    </c:if>
</nav>