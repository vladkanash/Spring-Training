<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cart" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order not found</title>
    <jsp:include page="../common/common-css.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="../header.jsp"/>

    <h4 class="text text-danger">Order with ID ${orderKey} cannot be found.</h4>
</div>

<jsp:include page="../common/common-js.jsp"/>
</body>
</html>
