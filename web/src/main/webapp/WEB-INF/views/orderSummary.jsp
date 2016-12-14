<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order Summary</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>


    <c:choose>
    <c:when test="${not empty order.orderItems}">
        <div class="row title-row">
            <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
                <h4 class="text-primary">
                    Order Summary
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <sf:form method="POST" modelAttribute="order">

                    <spring:bind path="firstName">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="firstName">First Name</label>
                            <sf:input path="firstName" class="form-control" id="firstName" placeholder="First Name"/>
                            <sf:errors path="firstName" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="firstName">
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
                            <sf:input path="lastName" class="form-control" id="contactPhone" placeholder="Your Phone"/>
                            <sf:errors path="lastName" class="control-label" />
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
                            <td><c:out value="${item.phone.price}"/>$</td>
                            <td><c:out value="${item.quantity}"/></td>
                            <td><c:out value="${item.quantity * item.phone.price}"/>$</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="col-xs-12 col-xm-12 col-md-5 col-lg-5 pull-right no-padding">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>Subtotal:</td>
                            <td>${order.totalPrice}$</td>
                        </tr>
                        <tr>
                        <tr>
                            <td>Shipping:</td>
                            <td>${shippingPrice}$</td>
                        </tr>
                        <tr>
                            <td><strong>Total:</strong></td>
                            <td><strong>${order.totalPrice + shippingPrice}$</strong></td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pull-right">
                        <input type="submit" class="btn btn-primary btn-lg" value="Submit Order"/>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                    <h4 class="text text-danger">Your cart is empty</h4>
                    <a href="<spring:url value="/productList"/>" class="btn btn-danger">Go to store</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>