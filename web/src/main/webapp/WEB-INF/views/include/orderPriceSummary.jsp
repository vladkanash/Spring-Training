<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>

<table class="table">
    <tbody>
        <tr>
            <td><spring:message code="priceInfo.subtotal"/></td>
            <td><phonify:currency value="${order.totalPrice}"/></td>
        </tr>
        <tr>
            <td><spring:message code="priceInfo.shipping"/></td>
            <td><phonify:currency value="${param.shippingPrice}" /></td>
        </tr>
        <tr>
            <td>
                <strong>
                    <spring:message code="priceInfo.total"/>
                </strong>
            </td>
            <td>
                <strong>
                    <phonify:currency value="${order.totalPrice + param.shippingPrice}" />
                </strong>
            </td>
        </tr>
    </tbody>
</table>