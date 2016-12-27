<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administration page</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="showLogin" value="false"/>
    </jsp:include>

    <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
        <c:choose><c:when test="${not empty orderList}">
            <table class="table table bordered">
                <thead>
                    <tr>
                        <td><strong><spring:message code="orderSummary.label.firstName"/></strong></td>
                        <td><strong><spring:message code="orderSummary.label.lastName"/></strong></td>
                        <td><strong><spring:message code="orderSummary.label.deliveryAddress"/></strong></td>
                        <td><strong><spring:message code="orderSummary.label.contactPhone"/></strong></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orderList}" var="order">
                        <tr>
                            <td><c:out value="${order.firstName}"/></td>
                            <td><c:out value="${order.lastName}"/></td>
                            <td><c:out value="${order.deliveryAddress}"/></td>
                            <td><c:out value="${order.contactPhone}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            Orders not found.
        </c:otherwise>
        </c:choose>
    </div>

</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>