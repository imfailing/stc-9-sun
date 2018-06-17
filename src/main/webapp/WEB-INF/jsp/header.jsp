<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
<div class="wrapper">
    <header class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Academy</a>
                    <p class="navbar-text navbar-right">Signed in as ${name} ${surname} (${email})</p>
                    <a class="btn btn-default navbar-btn btn-logout btn-head" href="/profile">Личный кабинет</a>
                    <a class="btn btn-default navbar-btn btn-my-page btn-head" href="#">Выйти</a>
                </div>
            </div>
        </nav>
    </header>
    <div class="middle">
        <%@ include file="menu.jsp"%>
        <div class="container">
            <main class="content">