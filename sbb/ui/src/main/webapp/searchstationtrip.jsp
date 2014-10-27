<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:flatTemplate pageHeader="Расписание станции">
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
                    return moment(maxDate).format('DD.MM.YYYY');
                }
                $('.js-station-select').selectize()[0].selectize.setValue('${param.station}');
                $('.js-timepicker').datetimepicker({
                    pickTime: false,
                    language: 'ru',
                    minDate: moment(new Date()).format('DD.MM.YYYY'),
                    maxDate: getMaxDate()
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <ul class="nav nav-tabs">
                    <li><a href="${pageContext.request.contextPath}/searchTrip">Поиск рейсов</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/searchStationTrips">Расписание станций</a></li>
                </ul>
            </div>
            <div class="col-md-8">
                <c:if test="${errors != null && !errors.isEmpty()}">
                    <div class="alert alert-danger">
                        <c:forEach var="error" items="${errors}">
                            <p>* ${error.value}</p>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="well">
                    <form role="form" action="${pageContext.request.contextPath}/searchStationTrips" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-2">Станция</label>
                            <div class="col-md-4">
                                <select class="js-station-select" placeholder="Выберете станцию..." name="station" required="required">
                                    <option value="">Выберете станцию...</option>
                                    <c:forEach var="station" items="${stations}">
                                        <option value="${station.getName()}">${station.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label col-md-1">Дата</label>
                            <div class="col-md-5">
                                <div class='input-group date js-timepicker'>
                                    <input type='text' class="form-control" placeholder="Дата..." name="date" required="required" value="${param.date}"/>
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
        <c:if test="${isPost && (stationTimetable == null || stationTimetable.isEmpty())}">По Вашему запросу нет найденных маршрутов</c:if>
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
    </jsp:body>
</t:flatTemplate>