<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="phonify" tagdir="/WEB-INF/tags" %>
<table class="table">
    <tbody>
    <tr>
        <td>Subtotal:</td>
        <td><phonify:currency value="${order.totalPrice}"/></td>
    </tr>
    <tr>
    <tr>
        <td>Shipping:</td>
        <td><phonify:currency value="${shippingPrice}" /></td>
    </tr>
    <tr>
        <td><strong>Total:</strong></td>
        <td>
            <strong>
                <phonify:currency value="${order.totalPrice + shippingPrice}" />
            </strong>
        </td>
    </tr>
    </tbody>
</table>