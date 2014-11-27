<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/9/25
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    hello page
    ${hello}
<%
    System.out.println((String) pageContext.getAttribute("hello"));
    System.out.println((String) request.getAttribute("hello"));
    System.out.println((String) session.getAttribute("hello"));
    System.out.println((String) application.getAttribute("hello"));
%>
</body>
</html>
