<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/30 0030
  Time: 上午 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Manager</title>
</head>
<body>

<sf:form id="logoutForm" action="${ctx}/logout" method="post">
    <a href="#" onclick="document.getElementById('logoutForm').submit();">注销</a>
</sf:form>
</body>
</html>
