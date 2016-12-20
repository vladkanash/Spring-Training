<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="productList.title"/></title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

    <div class="container">
        <jsp:include page="header.jsp"/>

        <div class="row title-row">
            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                <h4 class="text-primary">
                    <spring:message code="productList.header"/>
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12 col-xs-12 col-md-12">
                <c:if test="${not empty phoneList}">
                    <table class="table">
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
                        <c:forEach items="${phoneList}" var="phone">
                            <tr>
                                <sf:form method="POST" modelAttribute="productForm" class="productForm">
                                    <td><a href="/phone/${phone.key}"><c:out value="${phone.model}"/></a></td>
                                    <td><c:out value="${phone.color}"/></td>
                                    <td><phonify:currency value="${phone.price}"/></td>
                                    <td>
                                        <sf:input path="quantity" id="${phone.key}" type="text" class="form-control" maxlength="3"/>
                                        <span class="error text-danger"></span>
                                    </td>
                                    <td><sf:button type="submit" class="btn btn-sm btn-default">Add to cart</sf:button></td>
                                    <sf:hidden value="${phone.key}" path="productKey" />
                                </sf:form>
                            </tr>
                        </c:forEach>
                    </tbody>
                    </table>
                </c:if>
            </div>
        </div>
        <hr>
    </div>

    <jsp:include page="common/common-js.jsp"/>
    <spring:url value="/resources/js/productForm.js" var="productFormJs" />
    <script src="${productFormJs}"></script>

</body>
</html>