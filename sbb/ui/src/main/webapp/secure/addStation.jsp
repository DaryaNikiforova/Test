<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15.10.14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="station" menuRow="add" pageHeader="Новая станция">
    <jsp:body>
        <div class="row">
            <div class="col-md-4">
                <form role="form" action="addStation" method="post">
                    <div class="form-group">
                        <label>Имя станции:</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <button type="button" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>