<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cart" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="navbar.jsp"/>

<div class="row">
    <div class="col-lg-2 col-sm-6 col-xs-12 col-md-2">
        <img src="<c:url value="/resources/img/phonify-logo.png"/>" width="80%" height="80%"/>
    </div>
    <div class="col-lg-8 col-sm-2 col-xs-12 col-md-8">
    </div>
    <div class="col-lg-2 col-sm-4 col-xs-12 col-md-2" id="cartSummary">
        <a href="<spring:url value="/cartSummary"/>" class="btn btn-primary">My cart: ${productCount} items (${totalPrice}$)</a>
    </div>
</div>
<hr>