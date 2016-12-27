<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cart" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="navbar.jsp">
    <jsp:param name="showLogin" value="${param.showLogin}"/>
</jsp:include>

<div class="row">
    <div class="col-lg-2 col-sm-6 col-xs-12 col-md-2">
        <a href="<spring:url value="/productList"/>">
            <img src="<c:url value="/resources/img/phonify-logo.png"/>" width="80%" height="80%"/>
        </a>
    </div>
    <div class="col-lg-8 col-sm-2 col-xs-12 col-md-8">
    </div>
    <div class="col-lg-2 col-sm-4 col-xs-12 col-md-2" id="cartSummary">
        <a href="<spring:url value="/cartInfo"/>" class="btn btn-primary">
            <c:choose>
                <c:when test="${empty productCount or productCount == 0}">
                    My cart: empty
                </c:when>
                <c:otherwise>
                    My cart: ${productCount} items (${totalPrice}$)
                </c:otherwise>
            </c:choose>
        </a>
    </div>
</div>
<hr>