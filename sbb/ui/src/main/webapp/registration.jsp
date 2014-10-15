<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 12.10.14
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<t:maintemplate>
    <jsp:attribute name="header">
        <h1>Регистрация</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <p>Ticket service by T-systems Java school student</p>
    </jsp:attribute>
    <jsp:body>
        <form action = "registration" method = "post">
            <input type = "text" placeholder="Имя" name = "name">
            <input type = "text" placeholder="Фамилия" name = "surname">
            <input type = "text" placeholder="Дата рождения" name = "birth">
            <input type = "text" placeholder="Логин" name = "login">
            <input type = "text" placeholder="Пароль" name = "password">
            <input type = "SUBMIT">
        </form>
    </jsp:body>
</t:maintemplate>