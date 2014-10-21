<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15.10.14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<t:mainTemplate>
    <jsp:attribute name="header">
        <h1>Новая станция</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <p>Ticket service by T-systems Java school student</p>
    </jsp:attribute>
    <jsp:body>
        <form action = "addStation" method="post">
            <input type="text" name = "name" placeholder="Название станции">
            <input type="submit">
        </form>
    </jsp:body>
</t:mainTemplate>