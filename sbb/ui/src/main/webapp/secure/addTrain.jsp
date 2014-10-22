<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="train" menuRow="add" pageHeader="Новая станция">
    <jsp:body>
        <div class="row">
            <div class="col-md-4">
                <form role="form" action="addTrain" method="post">
                    <div class="form-group">
                        <label>Номер</label>
                        <input type="text" class="form-control" maxlength="10" placeholder="1099" name="number">
                    </div>
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" maxlength="100" placeholder="Сапсан" name="name">
                    </div>
                    <div class="form-group">
                        <label>Количество мест</label>
                        <input type="number" class="form-control" min="1" placeholder="100" maxlength="4" name="seatCount">
                    </div>
                    <div class="form-group">
                        <label>Тип поезда</label>
                        <select class="form-control" placeholder="Выберите тип..." name="rate">
                            <option value="">Выберите тип...</option>
                            <option value="1">Скорый</option>
                            <option value="2">Скоростной</option>
                            <option value="3">Ускоренный</option>
                            <option value="4">Фирменный</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-primary">Создать поезд</button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>