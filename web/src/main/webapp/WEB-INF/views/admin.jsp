<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="adminPage.title"/></title>
    <sec:csrfMetaTags />
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="showLogin" value="false"/>
    </jsp:include>
    <div class="row title-row">
        <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
            <h4 class="text-primary">
                <spring:message code="adminPage.header"/>
            </h4>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
            <c:choose><c:when test="${not empty orderList}">
                <table class="table table bordered">
                    <thead>
                        <tr>
                            <td><strong><spring:message code="adminPage.label.key"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.firstName"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.lastName"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.username"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.deliveryAddress"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.contactPhone"/></strong></td>
                            <td><strong><spring:message code="adminPage.label.totalPrice"/></strong></td>
                            <td><strong></strong></td>
                            <td><strong></strong></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td><c:out value="${order.key}"/></td>
                                <td><c:out value="${order.firstName}"/></td>
                                <td><c:out value="${order.lastName}"/></td>
                                <td><c:out value="${order.user.username}"/></td>
                                <td><c:out value="${order.deliveryAddress}"/></td>
                                <td><c:out value="${order.contactPhone}"/></td>
                                <td><phonify:currency value="${order.totalPrice}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.delivered}">
                                            <button onclick="deliveryOrder(${order.key})" class="btn btn-sm btn-default" disabled="disabled">
                                                <spring:message code="adminPage.message.order.shipped"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                        <button onclick="deliveryOrder(${order.key})" class="btn btn-sm btn-primary">
                                            <spring:message code="adminPage.button.delivery"/>
                                        </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="<spring:url value="/orderSummary/${order.key}"/>" class="btn btn-sm btn-default">
                                        <spring:message code="adminPage.button.orderSummary"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <spring:message code="adminPage.message.noOrders"/>
            </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

<script>
    function deliveryOrder(key) {
        var headers = {};

        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        headers[csrfHeader] = csrfToken;

        $.ajax({
            headers: headers,
            url: '/setDeliveredState/' + key,
            type: 'POST',
            success: function() {
                $(".table").load('/admin .table > *');
            }
        });
    }
</script>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>