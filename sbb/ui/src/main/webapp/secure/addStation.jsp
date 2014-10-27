<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15.10.14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:flatTemplate menuBlock="station" menuRow="add" pageHeader="Новая станция">
    <jsp:body>
        <c:set var="nameCondition" value="${errors.get('name').length() > 0}"/>

        <div class="row">
            <div class="col-lg-6">
                <div class="well">
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/secure/addStation" method="post">
                        <fieldset>
                            <div class="form-group <c:if test="${nameCondition}">has-error</c:if>">
                                <label class="control-label col-lg-3">Имя станции:</label>
                                <div class="col-lg-9">
                                    <input type="text" class="form-control" name="name" maxlength="100" value="${param.name}" required="required">
                                </div>
                                <c:if test="${nameCondition}"><label class="text-danger col-lg-12">${errors.get("name")}</label></c:if>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary pull-right">Создать</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:flatTemplate>