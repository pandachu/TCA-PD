<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
 
        session.removeAttribute("sUserName");
        session.removeAttribute("sPwd");
        session.invalidate();
        response.sendRedirect("../index.jsp");        
        %>
        <h1>Logout was done successfully.</h1>
         
    </body>
</html>
