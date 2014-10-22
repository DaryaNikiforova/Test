<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14.10.14
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<t:mainTemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Войдите в систему</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" action="login" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Логин" name="login" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Пароль" name="password" type="password" value="">
                                    </div>
                                    <div class="form-group">
                                        <p class="form-control-static">Еще не зарегистрировались? <a href="registration">Вам сюда!</a></p>
                                    </div>
                                    <button class="btn btn-lg btn-success btn-block">Вход</button>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:mainTemplate>
