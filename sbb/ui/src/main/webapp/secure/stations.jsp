<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<table>
<c:forEach var="station" items="${stations}">
    <tr>
        <td><c:out value="${station}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
