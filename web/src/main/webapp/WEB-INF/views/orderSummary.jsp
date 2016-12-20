<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="orderSummary.title"/></title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>

    <c:choose>
        <c:when test="${not empty order.orderItems}">
            <div class="row title-row">
                <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
                    <h4 class="text-primary">
                        <spring:message code="orderSummary.header"/>
                    </h4>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <jsp:include page="include/orderItemsTable.jsp" />
                    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 no-padding">
                        <jsp:include page="include/orderPriceSummary.jsp" >
                            <jsp:param name="shippingPrice" value="${order.shippingPrice}"/>
                        </jsp:include>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-1 col-lg-1">
                </div>
                <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td><strong><spring:message code="orderSummary.label.firstName"/></strong></td>
                                <td><c:out value="${order.firstName}"/></td>
                            </tr>
                            <tr>
                                <td><strong><spring:message code="orderSummary.label.lastName"/></strong></td>
                                <td><c:out value="${order.lastName}"/></td>
                            </tr>
                            <tr>
                                <td><strong><spring:message code="orderSummary.label.deliveryAddress"/></strong></td>
                                <td><c:out value="${order.deliveryAddress}"/></td>
                            </tr>
                            <tr>
                                <td><strong><spring:message code="orderSummary.label.contactPhone"/></strong></td>
                                <td><c:out value="${order.contactPhone}"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <jsp:include page="include/emptyCart.jsp"/>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="common/common-js.jsp"/>

</body>
</html>
