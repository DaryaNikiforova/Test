<%@tag description = "Page Template" pageEncoding="UTF-8"%>
<%@attribute name = "header" fragment = "true" %>
<%@attribute name = "footer" fragment = "true" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html>
<body>
    <div id = "pageheader">
     <jsp:invoke fragment="header" />
    </div>
    <div id = "body">
     <jsp:doBody />
    </div>
    <div id = "pagefooter">
     <jsp:invoke fragment="footer" />
    </div>
</body>
</html>