<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14.10.14
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainTemplate page="login" loggedIn="${loggedIn}" showAdmin="${showAdmin}">
    <jsp:body>
        <c:set var="loginCondition" value="${errors.get('login').length() > 0}"/>
        <c:set var="passwordCondition" value="${errors.get('password').length() > 0}"/>

        <h2 class="text-center title simple">Вход</h2>
        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <form role="form" action="${pageContext.request.contextPath}/login" method="post">
                    <fieldset>
                        <div class="form-group <c:if test="${loginCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Логин" name="login" value="${param.login}" type="text" autofocus maxlength="100">
                            <c:if test="${loginCondition}"><label class="control-label">${errors.get("login")}</label></c:if>
                        </div>
                        <div class="form-group <c:if test="${passwordCondition}">has-error</c:if>">
                            <input required="required" class="form-control" placeholder="Пароль" name="password" type="password" value="" maxlength="100">
                            <c:if test="${passwordCondition}"><label class="control-label">${errors.get("password")}</label></c:if>
                        </div>
                        <div class="form-group text-center">
                            <p class="form-control-static">Еще не зарегистрировались? <a href="${pageContext.request.contextPath}/registration">Вам сюда!</a></p>
                        </div>
                        <button class="btn btn-lg btn-default btn-block" type="submit">Войти</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:mainTemplate>
