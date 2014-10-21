<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 16:30
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
        <td>Имя</td>
        <td>Кол-во мест</td>
    </tr>
    <c:forEach var="train" items="${trains}">
        <tr>
            <td></td>
            <td><c:out value="${train.getNumber()}"/></td>
            <td><c:out value="${train.getName()}" /></td>
            <td><c:out value="${train.getSeatCount()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
