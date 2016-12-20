<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
        <h4 class="text text-danger"><spring:message code="cart.empty"/></h4>
        <a href="<spring:url value="/productList"/>" class="btn btn-danger">
            <spring:message code="cart.button.empty"/>
        </a>
    </div>
</div>