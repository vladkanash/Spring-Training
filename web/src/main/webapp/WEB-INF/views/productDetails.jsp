<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${phone.model}</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

    <div class="container">
        <jsp:include page="header.jsp"/>

        <div class="row title-row">
            <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
                <h4 class="text-primary">
                    Phone details
                </h4>
            </div>
            <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
                <h4 class="text-primary">
                    Add to cart
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-4 col-xs-12 col-md-4">
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="pull-right"><c:out value="${phone.model}"/></span>
                        Model
                    </li>
                    <li class="list-group-item">
                        <span class="pull-right"><c:out value="${phone.color}"/></span>
                        Color
                    </li>
                    <li class="list-group-item">
                        <span class="pull-right"><c:out value="${phone.price}$"/></span>
                        Price
                    </li>
                </ul>
            </div>

            <div class="col-sm-12 col-lg-4 col-xs-12 col-md-4">
            <sf:form method="POST" modelAttribute="productForm" class="productForm form-vertical">
                <div class="form-group">
                    <label class="control-label" for="${phone.key}">Quantity</label>
                    <div class="controls">
                        <sf:hidden value="${phone.key}" path="productKey" />
                        <sf:input path="quantity" id="${phone.key}" type="text" class="form-control" maxlength="3"/>
                        <span class="error text-danger"></span>
                    </div>
                </div>
                <div class="form-group">
                    <sf:button type="submit" class="btn btn-sm btn-primary">Add to cart</sf:button>
                </div>
            </sf:form>
            </div>
        </div>
    </div>

    <jsp:include page="common/common-js.jsp"/>
    <spring:url value="/resources/js/productForm.js" var="productFormJs" />
    <script src="${productFormJs}"></script>

</body>
</html>