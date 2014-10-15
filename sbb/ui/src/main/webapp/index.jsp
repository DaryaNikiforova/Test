<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<form action="user" method="post">
<input type = "text" placeholder="Имя" name = "name">
<input type = "text" placeholder="Фамилия" name = "surname">
<input type = "SUBMIT">
</form>
<table>
<tr>
    <td>Name</td>
    <td>Surname</td>
</tr>
<c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.getName()}"/></td>
        <td><c:out value="${user.getSurname()}"/></td>
    </tr>
</c:forEach>
</table>
<c:if test="${role == 'admin'}">
  <form action="secure/addStation" post = "post">
     <input type = "submit" value = "Добавить станцию">
  </form>
</c:if>
<form action="logout" method = "post">
    <input type = "submit" value = "Выйти">
</form>
</body>
</html>
