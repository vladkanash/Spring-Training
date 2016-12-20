<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="orderInfo.title"/></title>
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
                    <spring:message code="orderInfo.header"/>
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <sf:form method="POST" modelAttribute="orderInfoForm" id="orderSubmitForm" action="/submitOrder">

                    <spring:bind path="firstName">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="firstName"><spring:message code="orderInfo.label.firstName"/></label>
                            <spring:message code="orderInfo.placeholder.firstName" var="firstNamePlaceholder"/>
                            <sf:input path="firstName" class="form-control" id="firstName" placeholder="${firstNamePlaceholder}"/>
                            <sf:errors path="firstName" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="form-group form-horizontal  ${status.error ? 'has-error' : ''}">
                            <label for="lastName"><spring:message code="orderInfo.label.lastName"/></label>
                            <spring:message code="orderInfo.placeholder.lastName" var="lastNamePlaceholder"/>
                            <sf:input path="lastName" class="form-control" id="lastName" placeholder="${lastNamePlaceholder}"/>
                            <sf:errors path="lastName" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="deliveryAddress">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="deliveryAddress"><spring:message code="orderInfo.label.deliveryAddress"/></label>
                            <spring:message code="orderInfo.placeholder.deliveryAddress" var="addressPlaceholder"/>
                            <sf:input path="deliveryAddress" class="form-control" id="deliveryAddress" placeholder="${addressPlaceholder}"/>
                            <sf:errors path="deliveryAddress" class="control-label" />
                        </div>
                    </spring:bind>

                    <spring:bind path="contactPhone">
                        <div class="form-group form-horizontal ${status.error ? 'has-error' : ''}">
                            <label for="contactPhone"><spring:message code="orderInfo.label.contactPhone"/></label>
                            <spring:message code="orderInfo.placeholder.contactPhone" var="phonePlaceholder"/>
                            <sf:input path="contactPhone" class="form-control" id="contactPhone" placeholder="${phonePlaceholder}"/>
                            <sf:errors path="contactPhone" class="control-label" />
                        </div>
                    </spring:bind>

                </sf:form>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <jsp:include page="include/orderItemsTable.jsp" />
                <div class="col-xs-12 col-xm-12 col-md-5 col-lg-5 pull-right no-padding">
                    <jsp:include page="include/orderPriceSummary.jsp" >
                        <jsp:param name="shippingPrice" value="${shippingPrice}"/>
                    </jsp:include>
                    <div class="pull-right">
                        <button class="btn btn-primary btn-lg" onclick="$('#orderSubmitForm').submit()">
                            <spring:message code="orderInfo.button.submit"/>
                        </button>
                    </div>
                </div>
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