<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 20.10.14
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainTemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/resources/js/moment-with-locales.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/selectize.min.js"></script>
        <script type="text/javascript">
            $(function () {
                var select = $('.js-station-select').selectize();
                select[0].selectize.setValue('${param.stationFrom}');
                select[1].selectize.setValue('${param.stationTo}');
                $('.js-timepicker').datetimepicker({
                    language: 'ru'
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div id="wrapper">
            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-header">Поиск рейсов</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                                <form role="form" action="searchTrip" method="post" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-md-1">От</label>
                                        <select class="col-md-4 js-station-select" placeholder="Выберете станцию..." name="stationFrom">
                                            <option value="">Выберете станцию...</option>
                                            <c:forEach var="station" items="${stations}">
                                                <option value="${station.getName()}">${station.getName()}</option>
                                            </c:forEach>
                                        </select>
                                        <label class="control-label col-md-1">C</label>
                                        <div class='input-group date js-timepicker col-md-4'>
                                            <input type='text' class="form-control" placeholder="Дата и время..." name="departure" value="${param.departure}"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-1">До</label>
                                        <select class="col-md-4 js-station-select" placeholder="Выберете станцию..." name="stationTo">
                                            <option value="">Выберете станцию...</option>
                                            <c:forEach var="station" items="${stations}">
                                                <option value="${station.getName()}">${station.getName()}</option>
                                            </c:forEach>
                                        </select>
                                        <label class="control-label col-md-1">По</label>
                                        <div class='input-group date js-timepicker col-md-4'>
                                            <input type='text' class="form-control" placeholder="Дата и время..." name="arrival" value="${param.arrival}"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <input type="submit" class="btn btn-primary pull-right col-md-2" value="Найти">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <c:if test="${timetable != null && !timetable.isEmpty()}">
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
                                    <th>В пути</th>
                                    <th>Кол-во мест</th>
                                    <th>Цена</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${timetable}">
                                    <tr>
                                        <td>${entry.getTrainNumber()}</td>
                                        <td>${entry.getRouteName()}</br>${entry.getTrainName()}</td>
                                        <td>${entry.getDepDate()}</br>${entry.getStationFrom()}</td>
                                        <td>${entry.getArriveDate()}</br>${entry.getStationTo()}</td>
                                        <td>${"8ч. 1м"}</td>
                                        <td>${entry.getSeatCount()}</td>
                                        <td>${entry.getPrice()}</td>
                                        <td class="text-right"><a href="buyTicket?tripId=${entry.getTripId()}&stationFrom=${entry.getStationFrom()}&stationTo=${entry.getStationTo()}">купить билет</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:mainTemplate>
