<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="userPage.title"/></title>
    <sec:csrfMetaTags />
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="showLogin" value="true"/>
    </jsp:include>
    <div class="row title-row">
        <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
            <h4 class="text-primary">
                <spring:message code="userPage.header"/>
            </h4>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
            <c:choose><c:when test="${not empty orderList}">
                <table class="table table bordered">
                    <thead>
                    <tr>
                        <td><strong><spring:message code="userPage.label.key"/></strong></td>
                        <td><strong><spring:message code="userPage.label.firstName"/></strong></td>
                        <td><strong><spring:message code="userPage.label.lastName"/></strong></td>
                        <td><strong><spring:message code="userPage.label.totalPrice"/></strong></td>
                        <td><strong>Order Status</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderList}" var="order">
                        <tr>
                            <td><c:out value="${order.key}"/></td>
                            <td><c:out value="${order.firstName}"/></td>
                            <td><c:out value="${order.lastName}"/></td>
                            <td><phonify:currency value="${order.totalPrice}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.delivered}">
                                        <spring:message code="userPage.messages.order.shipped"/>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="userPage.messages.order.waitingForShipping"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="<spring:url value="/orderSummary/${order.key}"/>" class="btn btn-sm btn-default">
                                    <spring:message code="userPage.button.orderSummary"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
                <c:otherwise>
                    <spring:message code="userPage.message.noOrders"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>