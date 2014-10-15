<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14.10.14
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<t:mainTemplate>
    <jsp:attribute name="header">
        <h1>Регистрация</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <p>Ticket service by T-systems Java school student</p>
    </jsp:attribute>
    <jsp:body>
        <form action = "login" method = "post">
            <input type = "text" placeholder="Логин" name = "login">
            <input type = "text" placeholder="Пароль" name = "password">
            <input type = "SUBMIT">
        </form>
    </jsp:body>
</t:mainTemplate>
