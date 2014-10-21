<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <td>Номер</td>
        <td>Маршрут</td>
        <td>Время в пути</td>
        <td>Дистанция</td>
    </tr>
    <c:forEach var="route" items="${routes}">
        <tr>
            <td></td>
            <td><c:out value="${route.getNumber()}"/></td>
            <td><c:out value="${route.getRoute()}" /></td>
            <td><c:out value="${route.getTime()}" /></td>
            <td><c:out value="${route.getDistance()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
