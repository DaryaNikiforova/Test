<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 22.10.14
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<form action="purchasesummary" method="post">
    <table>
        <tr>
            <td>Маршрут поездки</td>
            <td>${ticket.getRoute()}</td>
        </tr>
        <tr>
            <td>Поезд</td>
            <td>${ticket.getTrip()}</td>
        </tr>
        <tr>
            <td>Departure</td>
            <td>${ticket.getDeparture()}</td>
        </tr>
        <tr>
            <td>Arrival</td>
            <td>${ticket.getArrival()}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${ticket.getUserName()}</td>
        </tr>
        <tr>
            <td>Surname</td>
            <td>${ticket.getUserSurname()}</td>
        </tr>
        <tr>
            <td>Birth</td>
            <td>${ticket.getBirthDate()}</td>
        </tr>
        <tr>
            <td>Place</td>
            <td>${ticket.getSeatNumber()}</td>
        </tr>
        <tr>
            <td>Type</td>
            <td>${ticket.getRateName()}</td>
        </tr>
        <tr>
            <td>Other staff</td>
            <td>
                <c:forEach var="s" items="${ticket.getServices()}">
                    <input type="checkbox" name = "services" value="${s.key}">${s.value}
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Цена</td>
            <td>${ticket.getPrice()}</td>
        </tr>
    </table>
</form>
</body>
</html>
