<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

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
                Thank you for your order!
            </h4>
        </div>
        <div class="col-lg-4 col-sm-12 col-xs-12 col-md-4">
            <h4 class="text-primary">
                Add to cart
            </h4>
        </div>
    </div>
</div>

<jsp:include page="common/common-js.jsp"/>

</body>
</html>
