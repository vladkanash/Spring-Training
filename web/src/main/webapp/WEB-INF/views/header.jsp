<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cart" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="navbar.jsp"/>

<div class="row">
    <div class="col-lg-2 col-sm-6 col-xs-12 col-md-2">
        <img src="<c:url value="/resources/img/phonify-logo.png"/>" width="80%" height="80%"/>
    </div>
    <div class="col-lg-7 col-sm-2 col-xs-12 col-md-7">
    </div>
    <div class="col-lg-3 col-sm-4 col-xs-12 col-md-3" id="cartSummary">
        <h4 class="text text-primary">
            Products in cart: ${productCount} (${totalPrice}$)
        </h4>
    </div>
</div>
<hr>