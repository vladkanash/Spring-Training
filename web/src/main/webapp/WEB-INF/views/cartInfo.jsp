<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="cartInfo.title"/></title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
            <button onclick="$('#cartForm').submit()" class="btn btn-primary">
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
                    <sf:form method="POST" modelAttribute="cartForm" action="/updateProduct" id="cartForm">
                        <c:forEach items="${productList}" var="item" varStatus="vs">
                            <tr>
                                <td><c:out value="${item.phone.model}"/></td>
                                <td><c:out value="${item.phone.color}"/></td>
                                <td><phonify:currency value="${item.phone.price}"/></td>
                                <td>
                                    <sf:hidden path="items[${vs.index}].productKey" value="${item.phone.key}"/>
                                    <sf:input path="items[${vs.index}].quantity" type="text"
                                              class="form-control" maxlength="3" value="${items[vs.index].quantity}"/>
                                    <sf:errors path="items[${vs.index}].quantity" cssClass="error text-danger"/>
                                </td>
                                <td>
                                    <button onclick="deleteProduct(${item.phone.key})" href="/deleteProduct/${item.phone.key}" class="btn btn-sm btn-default">
                                        <spring:message code="cartInfo.button.delete"/>
                                    </button>
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
            <jsp:include page="include/emptyCart.jsp"/>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="common/common-js.jsp"/>
<script>
    function deleteProduct(key) {
        var headers = {};

        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        headers[csrfHeader] = csrfToken;

        $.ajax({
            headers : headers,
            url: '/deleteProduct/' + key,
            type: 'DELETE'
        });
    }
</script>
</body>
</html>