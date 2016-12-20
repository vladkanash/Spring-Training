<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="cartInfo.title"/></title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>

    <c:choose>
    <c:when test="${not empty productList}">
    <div class="row title-row">
        <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
            <h4 class="text-primary">
                <spring:message code="cartInfo.header"/>
            </h4>
        </div>
        <div class="col-lg-2 col-sm-4 col-xs-12 col-md-2">
            <a href="<spring:url value="/orderInfo"/>" class="btn btn-primary">
                <spring:message code="cartInfo.button.order"/>
            </a>
            <button onclick="$('#productUpdateForm').submit()" class="btn btn-primary">
                <spring:message code="cartInfo.button.update"/>
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th><spring:message code="product.model"/></th>
                        <th><spring:message code="product.color"/></th>
                        <th><spring:message code="product.price"/></th>
                        <th><spring:message code="product.quantity"/></th>
                        <th><spring:message code="product.action"/></th>
                    </tr>
                </thead>
                <tbody>
                <sf:form method="POST" modelAttribute="productUpdateForm" action="/updateProduct" id="productUpdateForm">
                    <sf:errors path="productMap" cssClass="error text-danger"/>
                    <c:forEach items="${productList}" var="item">
                        <tr>
                            <td><c:out value="${item.phone.model}"/></td>
                            <td><c:out value="${item.phone.color}"/></td>
                            <td><phonify:currency value="${item.phone.price}"/></td>
                            <td>
                                <sf:input path="productMap[${item.phone.key}]" type="text" class="form-control" maxlength="3" value="${item.quantity}"/>
                                <sf:errors path="productMap[${item.phone.key}]" cssClass="error text-danger"/>
                            </td>
                            <td>
                                <a href="/deleteProduct/${item.phone.key}" class="btn btn-sm btn-default">
                                    <spring:message code="cartInfo.button.delete"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </sf:form>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row title-row">
        <div class="col-lg-10 col-sm-8 col-xs-12 col-md-10">
        </div>
        <div class="col-lg-2 col-sm-4 col-xs-12 col-md-2">
            <a href="<spring:url value="/orderInfo"/>" class="btn btn-primary">
                <spring:message code="cartInfo.button.order"/>
            </a>
        </div>
    </div>
    </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                    <h4 class="text text-danger"><spring:message code="cart.empty"/></h4>
                    <a href="<spring:url value="/productList"/>" class="btn btn-danger">
                        <spring:message code="cart.button.empty"/>
                    </a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="common/common-js.jsp"/>
</body>
</html>