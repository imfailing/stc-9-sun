<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">Состав</div>
    <!-- Таблица -->
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Группа</th>
            <th>Занятие</th>
            <th>Оценка</th>
            <th></th>
        </tr>
        </thead>
        <c:set var="number" value="0"/>
        <c:forEach items="${marks}" var="mark">
            <tbody>
            <tr>
                <td><a href="/group/${mark.lesson.group.id}/">${mark.lesson.group.title}</a></td>
                <td><a href="/group/${mark.lesson.group.id}/lessons/${mark.lesson.id}/">${mark.lesson.date}</a></td>
                <td>${mark.value}</td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

<%@include file="footer.jsp" %>
