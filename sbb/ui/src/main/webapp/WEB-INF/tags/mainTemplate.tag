<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description = "Page Template" pageEncoding="UTF-8"%>
<%@attribute name = "header" fragment = "true" %>
<%@attribute name = "footer" fragment = "true" %>
<%@attribute name = "showAdmin"%>
<%@attribute name = "loggedIn"%>
<%@attribute name = "page"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/magister.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/open-sans.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/wire-one.css" />
    <jsp:invoke fragment="header" />
</head>
<html>
<body class="text-shadows">
    <nav class="mainmenu">
        <div class="container">
            <div class="dropdown open">
                <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dLabel">
                    <c:if test="${page != 'index'}">
                        <li><a href="<%=request.getContextPath()%>/index">На главную</a></li>
                    </c:if>
                    <c:choose>
                        <c:when test="${loggedIn eq 'true'}">
                            <c:if test="${showAdmin eq 'true'}">
                                <li><a href="<%=request.getContextPath()%>/secure/getStations">Администрирование</a></li>
                            </c:if>
                            <li><a href="<%=request.getContextPath()%>/logout">Выйти</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="<%=request.getContextPath()%>/registration" <c:if test="${page eq 'registration'}">class="active"</c:if>>Регистрация</a></li>
                            <li><a href="<%=request.getContextPath()%>/login" <c:if test="${page eq 'login'}">class="active"</c:if>>Войти</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>

    <section class="section">
        <div class="container">
            <jsp:doBody />
        </div>
    </section>

     <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.js"></script>
     <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
     <jsp:invoke fragment="footer" />
</body>
</html>