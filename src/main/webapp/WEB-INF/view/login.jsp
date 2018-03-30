<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/30 0030
  Time: 上午 11:32
  To change this template use File | Settings | File Templates.
  ${pageContext.request.contextPath }
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body onload='document.f.username.focus();'>
<h3>Login with Username and Password</h3>
<form name='f' action='${pageContext.request.contextPath}login'
      method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"
                                   value="Login" /></td>
        </tr>
    </table>
</form>
</body>
</html>
