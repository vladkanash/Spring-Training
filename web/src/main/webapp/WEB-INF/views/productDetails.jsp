<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${phone.model}</title>
    <jsp:include page="common/common-css.jsp"/>
</head>
<body>

    <div class="container">
        <jsp:include page="header.jsp"/>

        <div class="row">
            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                <h4 class="text-primary">
                    Our Phones
                </h4>
            </div>
        </div>

        <%--<div class="row">--%>
            <%--<c:out value="${phone.model}"/>--%>
            <%--<c:out value="${phone.color}"/>--%>
            <%--<c:out value="${phone.price}"/>--%>
        <%--</div>--%>

        <div class="row">
            <div class="col-sm-12 col-lg-12 col-xs-12 col-md-12">
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="tag tag-default tag-pill float-xs-right">14</span>
                        Cras justo odio
                    </li>
                    <li class="list-group-item">
                        <span class="tag tag-default tag-pill float-xs-right">2</span>
                        Dapibus ac facilisis in
                    </li>
                    <li class="list-group-item">
                        <span class="tag tag-default tag-pill float-xs-right">1</span>
                        Morbi leo risus
                    </li>
                </ul>
            </div>
        </div>

    </div>

    <jsp:include page="common/common-js.jsp"/>
</body>
</html>
