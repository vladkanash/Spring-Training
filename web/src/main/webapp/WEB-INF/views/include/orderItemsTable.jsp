<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<table class="table table-bordered">
    <thead>
    <tr>
        <th><spring:message code="product.model"/></th>
        <th><spring:message code="product.color"/></th>
        <th><spring:message code="product.price"/></th>
        <th><spring:message code="product.quantity"/></th>
        <th><spring:message code="product.subtotal"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${order.orderItems}" var="item">
        <tr>
            <td><c:out value="${item.phone.model}"/></td>
            <td><c:out value="${item.phone.color}"/></td>
            <td><phonify:currency value="${item.phone.price}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td><phonify:currency value="${item.quantity * item.phone.price}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
