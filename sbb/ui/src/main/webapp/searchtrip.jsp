<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 20.10.14
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form action = "searchTrip" method = "post">
        <input type = "text" name = "stationFrom" placeholder = "Станция отправления">
        <input type = "text" name = "stationTo" placeholder = "Станция прибытия">
        <input type = "text" name = "departure" placeholder = "Отправление">
        <input type = "text" name = "arrival" placeholder = "Прибытие">
        <input type = "submit">
    </form>
    <strong>${tripEmpty}</strong>
    <table>
    <c:forEach var="entry" items="${timetable}">
        <tr>
            <td>${entry.getTrainNumber()}</td>
            <td>${entry.getStationFrom()}</td>
            <td>${entry.getStationTo()}</td>
            <td>${entry.getDepDate()}</td>
            <td>${entry.getArriveDate()}</td>
            <td><a href="buyTicket?tripId=${entry.getTripId()}&stationFrom=${entry.getStationFrom()}&stationTo=${entry.getStationTo()}">купить билет</a>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
