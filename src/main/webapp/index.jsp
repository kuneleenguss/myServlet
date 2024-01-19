<%@ page import="com.example.myservlet.logic.Model" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Model model = Model.getInstance(); %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ДОООмашняя страница по работе с пользователями</h1>
Введите ID пользователя (0 - для вывода всего списка пользователей)
<br/>
Доступно: <%out.print(model.getFromList().size());%>
<form method="get" action="get">
    <label>ID:
        <input type="text" name="id" required><br/>
    </label>
    <button type="submit">Поиск</button>
</form>
<a href="addUser.html">Создать нового пользователя</a><br/>
</body>
</html>