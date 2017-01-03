<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="login.title"/></title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="showLogin" value="false"/>
    </jsp:include>

    <c:url var="loginUrl" value="/login" />

    <div class="row">
        <div class="col-lg-2 col-xs-12"></div>
        <div class="col-lg-8 col-xs-12">
            <sf:form action="${loginUrl}" method="post" class="form-horizontal">
                <fieldset>
                    <div class="col-lg-12 col-xs-12">
                        <legend><spring:message code="login.header"/></legend>
                    </div>

                    <c:if test="${param.error ne null}">
                        <div class="col-lg-12 col-xs-12 has-error">
                            <span class="control-label">
                                Invalid username and/or password. Please check your data.
                            </span>
                        </div>
                    </c:if>

                    <div class="form-group col-lg-12 col-xs-12">
                        <label class="col-md-1 control-label" for="username"></label>
                        <div class="col-md-12 col-lg-12">
                            <spring:message code="login.placeholder.username" var="username"/>
                            <input style="width: 100%" id="username"
                                   name="username"
                                   type="text"
                                   placeholder="${username}"
                                   class="form-control input-md in">
                        </div>
                    </div>

                    <div class="form-group col-lg-12 col-xs-12">
                        <label class="col-md-1 control-label" for="password"></label>
                        <div class="col-md-12 col-lg-12">
                            <spring:message code="login.placeholder.password" var="password"/>
                            <input style="width: 100%" id="password"
                                   name="password"
                                   type="password"
                                   placeholder="${password}"
                                   class="form-control input-md">
                        </div>
                    </div>

                    <div class="form-group col-lg-12 col-cs-12">
                        <div class="col-md-6 col-lg-6">
                            <button id="login" name="login" class="btn btn-block btn-primary"><spring:message code="login.label.login"/></button>
                        </div>
                        <div class="col-md-6 col-lg-6">
                            <a href="<spring:url value="register"/>" id="register" name="register" class="btn btn-block btn-success">
                                <spring:message code="login.label.register"/>
                            </a>
                        </div>
                    </div>
                </fieldset>
            </sf:form>
        </div>
        <div class="col-lg-2 col-xs-12"></div>
    </div>

</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>