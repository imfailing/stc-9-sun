<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_USER')" var= "isUSer"/>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
<div class="wrapper">
    <header class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">

                    <c:if test= "${not isUSer}">
                        <a class="navbar-brand" href="/">Academy</a>
                        <p class="navbar-text navbar-right">Вы не вошли</p>
                    </c:if>
                    <c:if test= "${isUSer}">
                    <a class="navbar-brand" href="/">Academy</a>
                    <p class="navbar-text navbar-right">Вошли как <security:authentication property= "principal.firstName"/> <security:authentication property= "principal.lastName"/> под ролью <security:authentication property= "principal.roles"  var="roles" />
                        <c:forEach items="${roles}" var="role" varStatus="vs">
                            ${role.name}
                        </c:forEach>
                    </p>
                    <a class="btn btn-default navbar-btn btn-logout btn-head" href="/profile">Личный кабинет</a>
                    <a class="btn btn-default navbar-btn btn-my-page btn-head" href="/logout">Выйти</a>
                    </c:if>
                </div>
            </div>
        </nav>
    </header>
    <div class="middle">
        <%@ include file="menu.jsp"%>
        <div class="container">
            <main class="content">