<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить пользователя</title>
</head>
<body>
<form action="/update" method="post">
    <p>Login</p>
    <p><c:out value="${user.login}"/></p>
    <p>Name</p>
    <p><input type="text" name="name"></p>
    <c:set target="${user}" property="name" value="${name}"/>
    <p>Password</p>
    <p><input type="text" name="password"></p>
    <c:set target="${user}" property="password" value="${password}"/>
    <p><input type="submit"></p>
</form>
</body>
</html>