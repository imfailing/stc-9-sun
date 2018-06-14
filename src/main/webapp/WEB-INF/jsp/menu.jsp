<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="left-sidebar">
    <div class="panel panel-success">
        <div class="panel-heading"><strong>Меню</strong></div>
        <div class="panel-body">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/">Главная</a></li>
                <c:if test= "${not isUSer}">
                <li><a href="/signup">Регистрация</a></li>
                </c:if>
                <c:if test= "${isUSer}">
                <li><a href="/users">Пользователи</a></li>
                <li><a href="/groups">Группы</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</aside>
