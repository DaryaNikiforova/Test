<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 21.10.14
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<form action = "searchStationTrips" method = "post">
    <input type = "text" name = "stationFrom" placeholder = "Станция отправления">
    <input type = "text" name = "departureDate" placeholder = "Дата отправления">
    <input type = "submit">
</form>
<table>
    <c:forEach var="entry" items="${stationTimetable}">
        <tr>
            <td>${entry.getTrainNumber()}</td>
            <td>${entry.getStationFrom()}</td>
            <td>${entry.getStationTo()}</td>
            <td>${entry.getDepDate()}</td>
            <td>${entry.getArriveDate()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
