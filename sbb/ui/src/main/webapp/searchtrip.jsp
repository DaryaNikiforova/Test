<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>

<t:flatTemplate pageHeader="Поиск рейсов">
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/resources/js/moment-with-locales.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/selectize.min.js"></script>
        <script type="text/javascript">
            $(function () {
                var getMaxDate = function() {
                    var today = new Date();
                    var maxDate = new Date();
                    maxDate.setDate(today.getDate() + 45);
                    return moment(maxDate).format('DD.MM.YYYY HH:mm');
                };
                var select = $('.js-station-select').selectize();
                select[0].selectize.setValue('${param.stationFrom}');
                select[1].selectize.setValue('${param.stationTo}');

                $('.js-timepicker').datetimepicker({
                    language: 'ru',
                    minDate: moment(new Date()).format('DD.MM.YYYY HH:mm'),
                    maxDate: getMaxDate()
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="${pageContext.request.contextPath}/searchTrip">Поиск рейсов</a></li>
                    <li><a href="${pageContext.request.contextPath}/searchStationTrips">Расписание станций</a></li>
                </ul>
            </div>
            <div class="col-lg-8">
                <c:if test="${errors != null && !errors.isEmpty()}">
                    <div class="alert alert-danger">
                        <c:forEach var="error" items="${errors}">
                        <p>* ${error.value}</p>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="well">
                    <form role="form" action="${pageContext.request.contextPath}/searchTrip" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-lg-2">От</label>
                            <div class="col-lg-4">
                                <select class="js-station-select" placeholder="Выберете станцию..." name="stationFrom" required="required">
                                    <option value="">Выберете станцию...</option>
                                    <c:forEach var="station" items="${stations}">
                                        <option value="${station.getName()}">${station.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label col-md-1">C</label>
                            <div class="col-lg-5">
                                <div class='input-group date js-timepicker'>
                                    <input type='text' class="form-control" placeholder="Дата и время..." name="departure" value="${param.departure}" required="required"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-2">До</label>
                            <div class="col-lg-4">
                                <select class="js-station-select" placeholder="Выберете станцию..." name="stationTo" required="required">
                                    <option value="">Выберете станцию...</option>
                                    <c:forEach var="station" items="${stations}">
                                        <option value="${station.getName()}">${station.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label col-lg-1">По</label>
                            <div class="col-lg-5">
                                <div class='input-group date js-timepicker'>
                                    <input type='text' class="form-control" placeholder="Дата и время..." name="arrival" value="${param.arrival}" required="required"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-primary pull-right">Найти</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.row -->
        <c:if test="${isPost && (timetable == null || timetable.isEmpty())}">По Вашему запросу нет найденных маршрутов</c:if>
        <c:if test="${timetable != null && !timetable.isEmpty()}">
        <div class="row">
            <div class="col-lg-10">
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
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${timetable}">
                            <tr>
                                <td>${entry.getTrainNumber()}</td>
                                <td>${entry.getRouteName()}
                                    <c:if test="${entry.getTrainName().length() > 0}">
                                        </br>&laquo;${entry.getTrainName()}&raquo;
                                    </c:if>
                                </td>
                                <td>${f:formatDate(entry.getDepDate())}</br>${entry.getStationFrom()}</td>
                                <td>${f:formatDate(entry.getArriveDate())}</br>${entry.getStationTo()}</td>
                                <td>${entry.getTime()}</td>
                                <td>${entry.getSeatCount()}</td>
                                <td class="text-right"><a href="${pageContext.request.contextPath}/buyTicket?tripId=${entry.getTripId()}&stationFrom=${entry.getStationFrom()}&stationTo=${entry.getStationTo()}">купить билет</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </c:if>
    </jsp:body>
</t:flatTemplate>
