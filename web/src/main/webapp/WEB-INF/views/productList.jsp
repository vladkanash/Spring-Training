<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Details</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

    <div class="container">
        <jsp:include page="header.jsp"/>

        <div class="row">
            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                <h4 class="text-primary">
                    Our Phones
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12 col-xs-12 col-md-12">
                <c:if test="${not empty phoneList}">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Model</th>
                                <th>Color</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${phoneList}" var="phone">
                                <tr>
                                    <sf:form method="POST" modelAttribute="productForm" class="productForm">
                                        <td><a href="/phone/${phone.key}"><c:out value="${phone.model}"/></a></td>
                                        <td><c:out value="${phone.color}"/></td>
                                        <td><c:out value="${phone.price}"/>$</td>
                                        <td>
                                            <sf:input path="quantity" id="${phone.key}" type="text" class="form-control" maxlength="3"/>
                                            <span class="error text-danger"></span>
                                        </td>
                                        <td><sf:button type="submit" class="btn btn-sm btn-primary">Add to cart</sf:button></td>
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