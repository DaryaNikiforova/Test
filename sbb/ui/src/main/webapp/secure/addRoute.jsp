<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="route" menuRow="add" pageHeader="Новый маршрут">
    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/resources/js/selectize.min.js"></script>
        <script type="text/javascript">
            $(function () {
                var stationOptions = [
                    <c:forEach var="station" items="${stations}">
                    {value: '${station.getName()}', text: '${station.getName()}'},
                    </c:forEach>
                ];


                var stationCount = 2;
                var lastPanel = $('.js-last-panel');
                var select = $('.js-station-select').selectize();
                    select[0].selectize.setValue('${param.name1}');
                    select[1].selectize.setValue('${param.name2}');

                var withIncreasedCount = function(func) {
                    func(++stationCount);
                }
                var withDeacreasedCount = function(func) {
                    func(--stationCount);
                }

                var getRemoveControl = function(count) {
                    return "<div class=\"form-group js-remove-control" + count + "\">" +
                            "<div class=\"col-md-2 pull-right\">" +
                            "<a class=\"js-remove-station" + count + "\" style=\"cursor: pointer;\">удалить</a>" +
                            "</div></div>";
                }

                var getStationControl = function(count) {
                    return "<div class=\"panel panel-default js-panel" + count + "\">" +
                            "<div class=\"panel-body\">" +
                            "<div class=\"form-group\">" +
                            "<label class=\"control-label col-md-4\">Станция №" + count + "</label>" +
                            "<select class=\"col-md-8 js-station-select" + count + "\" placeholder=\"Выберете станцию...\"  name=\"name" + count + "\" required>" +
                            "<option value=\"\">Выберете станцию...</option>" +
                            "</select>" +
                            "</div>" +
                            "<div class=\"form-group\">" +
                            "<label class=\"control-label col-md-4\">Время прибытия</label>" +
                            "<p class=\"form-control-static col-md-2 text-right\">часы</p>" +
                            "<div class=\"col-md-2\"><input type=\"number\" class=\"form-control\" min=\"0\" placeholder=\"0\" maxlength=\"4\" name=\"hour" + count + "\" required></div>" +
                            "<p class=\"form-control-static col-md-2 text-right\">минуты</p>" +
                            "<div class=\"col-md-2\"><input type=\"number\" class=\"form-control\" min=\"0\" max=\"60\" placeholder=\"0\" name=\"minute" + count + "\" required></div>" +
                            "</div>" +
                            "<div class=\"form-group\">" +
                            "<label class=\"control-label col-md-4\">Дистанция</label>" +
                            "<div class=\"col-md-2\"><input type=\"number\" min=\"0\" class=\"form-control\" placeholder=\"0\" maxlength=\"5\" name=\"distance" + count + "\" required></div>" +
                            "</div>" + getRemoveControl(count) + "</div></div>";
                }

                var setRemoveStationAction = function(count) {
                    $('.js-remove-station' + count).click(function() {
                        $('.js-panel' + count).remove();
                        withDeacreasedCount(function(decreasedCount) {
                            $('.js-panel' + decreasedCount).append(getRemoveControl(decreasedCount));
                            setRemoveStationAction(decreasedCount);
                        });
                    });
                }

                $('.js-add-station').click(function() {
                    withIncreasedCount(function(count) {
                        $(lastPanel).before(getStationControl(count));
                        var select = $('.js-station-select' + count).selectize()[0].selectize;
                        for(var i = 0; i < stationOptions.length; i++) {
                            select.addOption(stationOptions[i]);
                        }
                        $('.js-remove-control' + (count-1)).remove();
                        setRemoveStationAction(count);
                    });
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-md-8">
                <form role="form" class="form-horizontal" action="addRoute" method="post">
                    <c:if test="${errors != null && !errors.isEmpty()}">
                    <div class="form-group has-error">
                        <c:forEach var="error" items="${errors}">
                            <label class="control-label">* ${error.value}</label>
                        </c:forEach>
                    </div>
                    </c:if>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="control-label col-md-4">Станция №1</label>
                                <select class="col-md-8 js-station-select" placeholder="Выберете станцию..." name="name1" required>
                                    <option value="">Выберете станцию...</option>
                                    <c:forEach var="station" items="${stations}">
                                        <option value="${station.getName()}">${station.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="control-label col-md-4">Станция №2</label>
                                <select class="col-md-8 js-station-select" placeholder="Выберете станцию..." name="name2" required>
                                    <option value="">Выберете станцию...</option>
                                    <c:forEach var="station" items="${stations}">
                                        <option value="${station.getName()}">${station.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">Время прибытия</label>
                                <p class="form-control-static col-md-2 text-right">часы</p>
                                <div class="col-md-2"><input type="number" class="form-control" min="0" placeholder="0" maxlength="4" name="hour2" required value="${param.hour2}"></div>
                                <p class="form-control-static col-md-2 text-right">минуты</p>
                                <div class="col-md-2"><input type="number" class="form-control" min="0" max="60" placeholder="0" name="minute2" required value="${param.minute2}"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">Дистанция</label>
                                <div class="col-md-2"><input type="number" min="0" class="form-control" placeholder="0" maxlength="5" name="distance2" required value="${param.distance2}"></div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default js-last-panel">
                        <div class="panel-body text-center">
                            <a class="js-add-station" style="cursor: pointer;">+ добавить станцию</a>
                        </div>
                    </div>

                    <input type="submit" class="btn btn-primary pull-right" value="Создать маршрут">
                </form>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>
</body>
</html>
