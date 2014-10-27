<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 12.10.14
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainTemplate page="registration" loggedIn="${loggedIn}" showAdmin="${showAdmin}">
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/resources/js/moment-with-locales.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript">
            $(function () {
                var getMinDate = function() {
                    var today = new Date();
                    var minDate = new Date();
                    minDate.setYear(today.getYear() - 18);
                    return moment(minDate).format('DD.MM.YYYY');
                }
                $('.js-timepicker').datetimepicker({
                    pickTime: false,
                    language: 'ru',
                    minDate: '01.01.1900',
                    maxDate: getMinDate(),
                    showToday: false,
                    defaultDate: getMinDate()
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <c:set var="nameCondition" value="${errors.get('name').length() > 0}"/>
        <c:set var="surnameCondition" value="${errors.get('surname').length() > 0}"/>
        <c:set var="loginCondition" value="${errors.get('login').length() > 0}"/>
        <c:set var="passwordCondition" value="${errors.get('password').length() > 0}"/>
        <c:set var="birthCondition" value="${errors.get('birth').length() > 0}"/>

        <h2 class="text-center title simple">Регистрация</h2>
        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
                        <form role="form" action="${pageContext.request.contextPath}/registration" method="post">
                    <fieldset>
                        <div class="form-group <c:if test="${loginCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Логин" name="login" value="${param.login}" type="text" autofocus maxlength="100">
                            <c:if test="${loginCondition}"><label class="control-label">${errors.get("login")}</label></c:if>
                        </div>
                        <div class="form-group <c:if test="${passwordCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Пароль" name="password" type="password" value="" maxlength="100">
                            <c:if test="${passwordCondition}"><label class="control-label">${errors.get("password")}</label></c:if>
                        </div>
                        <div class="form-group <c:if test="${nameCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Имя" name="name" type="text" value="${param.name}" maxlength="100">
                            <c:if test="${nameCondition}"><label class="control-label">${errors.get("name")}</label></c:if>
                        </div>
                        <div class="form-group <c:if test="${surnameCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Фамилия" name="surname" type="text" value="${param.surname}" maxlength="100">
                            <c:if test="${surnameCondition}"><label class="control-label">${errors.get("surname")}</label></c:if>
                        </div>
                        <div class="form-group <c:if test="${birthCondition}">has-error</c:if>">
                            <div class='input-group date js-timepicker'>
                                <input type='text' class="form-control" name="birth" placeholder="Дата рождения" required="required" value="${param.birth}" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <c:if test="${birthCondition}"><label class="control-label">${errors.get("birth")}</label></c:if>
                        </div>
                        <button class="btn btn-lg btn-default btn-block" type="submit">Зарегистрироваться</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:mainTemplate>