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
        <c:set var="numCondition" value="${errors.get('number').length() > 0}"/>
        <c:set var="nameCondition" value="${errors.get('name').length() > 0}"/>
        <c:set var="seatCondition" value="${errors.get('seatCount').length() > 0}"/>
        <c:set var="rateCondition" value="${errors.get('rate').length() > 0}"/>

        <div class="row">
            <div class="col-md-4">
                <form role="form" action="addTrain" method="post">
                    <div class="form-group <c:if test="${numCondition}">has-error</c:if>">
                        <label>Номер</label>
                        <input type="number" class="form-control" min="1" maxlength="7" placeholder="1099" name="number" value="${param.number}" required>
                        <c:if test="${numCondition}"><label class="control-label">${errors.get("number")}</label></c:if>
                    </div>
                    <div class="form-group <c:if test="${nameCondition}">has-error</c:if>">
                        <label>Имя</label>
                        <input type="text" class="form-control" maxlength="100" placeholder="Сапсан (не обязательно)" name="name" value="${param.name}">
                        <c:if test="${nameCondition}"><label class="control-label">${errors.get("name")}</label></c:if>
                    </div>
                    <div class="form-group <c:if test="${seatCondition}">has-error</c:if>">
                        <label>Количество мест</label>
                        <input type="number" class="form-control" min="1" placeholder="100" maxlength="4" name="seatCount" value="${param.seatCount}" required>
                        <c:if test="${seatCondition}"><label class="control-label">${errors.get("seatCount")}</label></c:if>
                    </div>
                    <div class="form-group <c:if test="${rateCondition}">has-error</c:if>">
                        <label>Тип поезда</label>
                        <select class="form-control" placeholder="Выберите тип..." name="rate" required>
                            <option value="">Выберите тип...</option>
                            <c:forEach var="rate" items="${rates}">
                                <option value="${rate.getId()}" <c:if test="${rate.getId() eq param.rate}">selected="selected"</c:if>>
                                    ${rate.getName()}
                                </option>
                            </c:forEach>
                        </select>
                        <c:if test="${rateCondition}"><label class="control-label">${errors.get("rate")}</label></c:if>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Создать поезд">
                </form>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>