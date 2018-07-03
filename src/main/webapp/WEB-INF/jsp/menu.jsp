<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="left-sidebar">
    <div class="panel panel-success">
        <div class="panel-heading"><strong>Меню</strong></div>
        <div class="panel-body">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/">Главная</a></li>
                <sec:authorize access="isAnonymous()">
                    <li><a href="/signup">Регистрация</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/users">Пользователи</a></li>
                    <li><a href="/groups">Группы</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="/marks">Оценки</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</aside>
