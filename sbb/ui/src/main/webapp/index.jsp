<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

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
