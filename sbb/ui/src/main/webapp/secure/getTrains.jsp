<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="train" menuRow="get" pageHeader="Поезда">
    <jsp:body>
        <div class="row">
        <div class="col-md-8">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Номер поезда</th>
                        <th>Имя</th>
                        <th>Количество мест</th>
                        <th>Тип поезда</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${trains}">
                        <tr>
                            <td>${train.getNumber()}</td>
                            <td>${train.getName()}</td>
                            <td>${train.getSeatCount()}</td>
                            <td>${train.getRateName()}</td>
                            <td><a href="#">редактировать</a></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"><a href="${pageContext.request.contextPath}/secure/addTrain">+ добавить поезд</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>
