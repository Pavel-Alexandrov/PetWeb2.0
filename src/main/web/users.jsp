<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        TABLE {
            width: 700px;
            border-collapse: collapse;
        }
        TD, TH {
            padding: 3px;
            border: 1px solid black;
            text-align: center;
        }
        TH {
            background: #b0e0e6;
        }
    </style>
</head>
<h1>Текущие пользователи:</h1>
<table>
    <tr>
        <td>id</td>
        <td>login</td>
        <td>name</td>
        <td>password</td>
        <td>Эксклюзивные возможности</td>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td>
                <form action="/delete/${user.id}" method="post">
                    <button>Удалить пользователя</button>
                </form>
                <form action="/update/${user.id}" method="get">
                    <button>Изменить пользователя:</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    ${newUser}">Добавить пользователя:</button>
</form></p>
</body>
</html>

