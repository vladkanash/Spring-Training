<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="showLogin" value="false"/>
    </jsp:include>

    <c:url var="loginUrl" value="/login" />

    <div class="row title-row">
        <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
            <h4 class="text-primary">
                Register
            </h4>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <sf:form modelAttribute="userForm" action='/register' method="POST">

                <spring:bind path="username">
                    <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                        <label for="username">Username</label>
                        <sf:input path="username"
                                  type="text"
                                  id="username"
                                  name="username"
                                  class="form-control register-input"
                                  value="${userForm.username}"/>
                        <p class="help-block ${status.error ? 'hidden' : ''}">Username can contain any letters or numbers, without spaces</p>
                        <sf:errors path="username" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                        <label for="password">Password</label>
                        <sf:password path="password"
                                     name="password"
                                     class="form-control register-input"
                                     value="${userForm.password}"/>
                        <p class="help-block ${status.error ? 'hidden' : ''}">Password should be at least 6 characters</p>
                        <sf:errors path="password" class="control-label"/>
                    </div>
                </spring:bind>

                <spring:bind path="matchingPassword">
                    <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                        <label for="password_confirm">Password (Confirm)</label>
                        <sf:input path="matchingPassword"
                                  type="password"
                                  id="password_confirm"
                                  name="password_confirm"
                                  class="form-control register-input"
                                  value="${userForm.matchingPassword}"/>
                        <p class="help-block ${status.error ? 'hidden' : ''}">Please confirm password</p>
                        <sf:errors path="matchingPassword" class="control-label"/>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <div class="controls">
                        <button class="btn btn-success">Register</button>
                    </div>
                </div>
            </sf:form>
        </div>
    </div>
</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>