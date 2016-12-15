<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table">
    <tbody>
    <tr>
        <td>Subtotal:</td>
        <td><fmt:formatNumber type="currency"
                              minFractionDigits="2"
                              currencySymbol="$"
                              value="${order.totalPrice}"/></td>
    </tr>
    <tr>
    <tr>
        <td>Shipping:</td>
        <td>
            <fmt:formatNumber type="currency"
                              minFractionDigits="2"
                              currencySymbol="$"
                              value="${shippingPrice}" />
        </td>
    </tr>
    <tr>
        <td><strong>Total:</strong></td>
        <td>
            <strong>
                <fmt:formatNumber type="currency"
                                  minFractionDigits="2"
                                  currencySymbol="$"
                                  value="${order.totalPrice + shippingPrice}" />
            </strong>
        </td>
    </tr>
    </tbody>
</table>