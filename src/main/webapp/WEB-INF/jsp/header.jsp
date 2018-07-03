<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
<div class="wrapper">
    <header class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">

                    <sec:authorize access="isAnonymous()">
                        <a class="navbar-brand" href="/">Academy</a>
                        <p class="navbar-text navbar-right">Вы не вошли</p>
                        <a class="btn btn-default navbar-btn btn-my-page btn-head" href="/login">Войти</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand" href="/">Academy</a>
                    <p class="navbar-text navbar-right">Вошли как <sec:authentication property= "principal.firstName"/> <sec:authentication property= "principal.lastName"/> под ролью <sec:authentication property= "principal.roles"  var="roles" />
                        <c:forEach items="${roles}" var="role" varStatus="vs">
                            ${role.name}
                        </c:forEach>
                    </p>
                    <a class="btn btn-default navbar-btn btn-logout btn-head" href="/profile">Личный кабинет</a>
                    <a class="btn btn-default navbar-btn btn-my-page btn-head" href="/logout">Выйти</a>
                    </sec:authorize>
                </div>
            </div>
        </nav>
    </header>
    <div class="middle">
        <%@ include file="menu.jsp"%>
        <div class="container">
            <main class="content">