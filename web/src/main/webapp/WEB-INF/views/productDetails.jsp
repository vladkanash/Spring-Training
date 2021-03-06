<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${phone.model}</title>
    <sec:csrfMetaTags />
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

    <div class="container">
        <jsp:include page="header.jsp">
            <jsp:param name="showLogin" value="true"/>
        </jsp:include>

        <div class="row title-row">
            <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
                <h4 class="text-primary">
                    <spring:message code="productDetails.header.left"/>
                </h4>
            </div>
            <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
                <h4 class="text-primary">
                    <spring:message code="productDetails.header.right"/>
                </h4>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-4 col-xs-12 col-md-4">
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="pull-right"><c:out value="${phone.model}"/></span>
                        <spring:message code="product.model"/>
                    </li>
                    <li class="list-group-item">
                        <span class="pull-right"><c:out value="${phone.color}"/></span>
                        <spring:message code="product.color"/>
                    </li>
                    <li class="list-group-item">
                        <span class="pull-right"><phonify:currency value="${phone.price}"/></span>
                        <spring:message code="product.price"/>
                    </li>
                </ul>
            </div>

            <div class="col-sm-12 col-lg-4 col-xs-12 col-md-4">
            <form method="POST" class="productForm form-vertical">
                <div class="form-group">
                    <label class="control-label" for="${phone.id}"><spring:message code="product.quantity"/></label>
                    <div class="controls">
                        <input name="productKey" type="hidden" value="${phone.id}"  />
                        <input name="quantity" id="${phone.id}" type="text"
                               class="form-control"
                               maxlength="3"
                               value="1"/>
                        <span class="error text-danger"></span>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-primary">
                        <spring:message code="cart.button.add"/>
                    </button>
                </div>
            </form>
            </div>
        </div>
    </div>

    <jsp:include page="common/common-js.jsp"/>
    <spring:url value="/resources/js/productForm.js" var="productFormJs" />
    <script src="${productFormJs}"></script>

</body>
</html>
