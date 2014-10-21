<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 20.10.14
  Time: 0:33
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
        <td>Отправление</td>
        <td>Прибытие</td>
        <td>Всего мест</td>
    </tr>
<c:forEach var="trip" items="${trips}">
    <tr>
        <td><c:out value="${trip.getNumber()}"/></td>
        <td><c:out value="${trip.getRoute()}"/></td>
        <td><c:out value="${trip.getDeparture()}"/></td>
        <td><c:out value="${trip.getArrival()}"/></td>
        <td><c:out value="${trip.getSeatCount()}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
