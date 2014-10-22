<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 12.10.14
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<t:mainTemplate>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/resources/js/moment-with-locales.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript">
            $(function () {
                $('.js-timepicker').datetimepicker({
                    pickTime: false,
                    language: 'ru'
                });
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Регистрация</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="registration" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Логин" name="login" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Пароль" name="password" type="password" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Имя" name="name" type="text" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Фамилия" name="surname" type="text" value="">
                                </div>
                                <div class="form-group">
                                    <div class='input-group date js-timepicker'>
                                        <input type='text' class="form-control" name="birth" placeholder="Дата рождения" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <button class="btn btn-lg btn-success btn-block">Зарегистрироваться</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:mainTemplate>