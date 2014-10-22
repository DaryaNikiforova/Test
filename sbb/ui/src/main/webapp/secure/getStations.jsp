<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 19.10.14
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminTemplate menuBlock="station" menuRow="get" pageHeader="Станции">
    <jsp:body>
        <div class="row">
            <div class="col-md-6">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Имя станции</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="station" items="${stations}">
                            <tr>
                                <td>${station}</td>
                                <td class="text-right"><a href="#">редактировать</a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2"><a href="${pageContext.request.contextPath}/secure/addStation">+ добавить станцию</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:adminTemplate>