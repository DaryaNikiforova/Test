<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 20.10.14
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="trip" menuRow="get" pageHeader="Рейсы">
    <jsp:body>
        <div class="row">
            <div class="col-md-8">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Номер</th>
                            <th>Маршрут</th>
                            <th>Отправление</th>
                            <th>Прибытие</th>
                            <th>Всего мест</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="trip" items="${trips}">
                            <tr>
                                <td><c:out value="${trip.getNumber()}"/></td>
                                <td><c:out value="${trip.getRoute()}"/></td>
                                <td><c:out value="${trip.getDeparture()}"/></td>
                                <td><c:out value="${trip.getArrival()}"/></td>
                                <td><c:out value="${trip.getSeatCount()}"/></td>
                                <td class="text-right"><a href="#">редактировать</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>