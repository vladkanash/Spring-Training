<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order Info</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>


        <div class="row title-row">
            <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
                <h4 class="text-primary">
                    Order Summary
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <sf:form method="POST" modelAttribute="order" id="orderSubmitForm" action="/submitOrder">

                    <spring:bind path="firstName">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="firstName">First Name</label>
                            <sf:input path="firstName" class="form-control" id="firstName" placeholder="First Name"/>
                            <sf:errors path="firstName" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="form-group form-horizontal  ${status.error ? 'has-error' : ''}">
                            <label for="lastName">Last Name</label>
                            <sf:input path="lastName" class="form-control" id="lastName" placeholder="Last Name"/>
                            <sf:errors path="lastName" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="deliveryAddress">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="deliveryAddress">Delivery Address</label>
                            <sf:input path="deliveryAddress" class="form-control" id="deliveryAddress" placeholder="Your Address"/>
                            <sf:errors path="deliveryAddress" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="contactPhone">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="contactPhone">Contact Phone</label>
                            <sf:input path="contactPhone" class="form-control" id="contactPhone" placeholder="Phone Number"/>
                            <sf:errors path="contactPhone" class="control-label" />
                        </div>
                    </spring:bind>

                </sf:form>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Model</th>
                        <th>Color</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${order.orderItems}" var="item">
                        <tr>
                            <td><c:out value="${item.phone.model}"/></td>
                            <td><c:out value="${item.phone.color}"/></td>
                            <td><phonify:currency value="${item.phone.price}"/></td>
                            <td><c:out value="${item.quantity}"/></td>
                            <td><phonify:currency value="${item.quantity * item.phone.price}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="col-xs-12 col-xm-12 col-md-5 col-lg-5 pull-right no-padding">
                    <jsp:include page="include/orderPriceSummary.jsp" />
                    <div class="pull-right">
                        <button class="btn btn-primary btn-lg" onclick="$('#orderSubmitForm').submit()">Submit Order</button>
                    </div>
                </div>
            </div>
        </div>
</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>