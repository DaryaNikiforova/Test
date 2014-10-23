<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 21.10.14
  Time: 15:21
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
                $('.js-station-select').selectize()[0].selectize.setValue('${param.stationFrom}');
                $('.js-timepicker').datetimepicker({
                    pickTime: false,
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
                        <h1 class="page-header">Расписание станции</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                                <form role="form" action="searchStationTrips" method="post" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-md-2">Станция</label>
                                        <select class="col-md-4 js-station-select" placeholder="Выберете станцию..." name="stationFrom">
                                            <option value="">Выберете станцию...</option>
                                            <c:forEach var="station" items="${stations}">
                                                <option value="${station.getName()}">${station.getName()}</option>
                                            </c:forEach>
                                        </select>
                                        <label class="control-label col-md-2">Дата</label>
                                        <div class="col-md-4">
                                            <div class='input-group date js-timepicker'>
                                                <input type='text' class="form-control" placeholder="Дата..." name="departureDate" value="${param.departureDate}"/>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="submit" class="btn btn-primary pull-right col-md-2" value="Найти">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <c:if test="${stationTimetable != null && !stationTimetable.isEmpty()}">
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
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${stationTimetable}">
                                    <tr>
                                        <td>${entry.getTrainNumber()}</td>
                                        <td>${entry.getRouteName()}</br>${entry.getTrainName()}</td>
                                        <td>${entry.getDepDate()}</br>${entry.getStationFrom()}</td>
                                        <td>${entry.getArriveDate()}</br>${entry.getStationTo()}</td>
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