<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
    <div class="panel panel-default">
        <!-- Содержание панели по умолчанию -->
        <div class="panel-heading">Ближайшие занятия</div>
        <!-- Таблица -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Группа</th>
                <th>Дата</th>
                <th>Тема</th>
                <th>Описание</th>
                <th></th>
            </tr>
            </thead>
            <c:set var="number" value="0"/>
            <c:forEach items="${lessons}" var="lesson">
                <tbody>
                <tr>
                    <td>${number=number+1}</td>
                    <td>${lesson.group.title}</td>
                    <td>${lesson.date}</td>
                    <td>${lesson.title}</td>
                    <td>${lesson.description}</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <div class="panel-heading">Последние оценки</div>
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
                    <td>${mark.lesson.group.title}</td>
                    <td>${mark.lesson.date}</td>
                    <td>${mark.value}</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
<%@include file="footer.jsp" %>
