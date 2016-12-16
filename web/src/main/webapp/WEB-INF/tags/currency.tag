<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="value" required="true" type="java.lang.String" description="Currency value to display" %>

<fmt:formatNumber type="currency"
                  minFractionDigits="2"
                  maxFractionDigits="2"
                  currencySymbol="$"
                  value="${value}" />