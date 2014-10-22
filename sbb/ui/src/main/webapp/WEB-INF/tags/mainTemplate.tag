<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description = "Page Template" pageEncoding="UTF-8"%>
<%@attribute name = "header" fragment = "true" %>
<%@attribute name = "footer" fragment = "true" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/metisMenu.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/sb-admin-2.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/selectize.bootstrap3.css" />
    <jsp:invoke fragment="header" />
</head>
<html>
<body>
     <jsp:doBody />

     <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.js"></script>
     <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
     <script src="<%=request.getContextPath()%>/resources/js/metisMenu.min.js"></script>
     <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.js"></script>
     <jsp:invoke fragment="footer" />
</body>
</html>