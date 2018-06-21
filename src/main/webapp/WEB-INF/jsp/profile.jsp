<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="panel panel-default">
    <div class="panel-heading">Фамилия Имя Отчество</div>
    <table class="table">
            <tr>
                <td>${user.fullName}</td>
            </tr>

    </table>


</div>
<div class="panel panel-default">
    <div class="panel-heading">Список групп</div>
    <table class="table">
        <c:forEach items="${groups}" var="group">
            <tr>
                <td>
                        ${group.title}
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
<div class="panel panel-default">
    <div class="panel-heading">Настройки</div>
    <div class="well">
        <form:form method="POST" action="">
    <p>Отображать на главной:</p>
    <p><input type="checkbox" name="groups_list" value="true"> Список групп</p>
    <p><input type="checkbox" name="grades" value="true"> Последние оценки</p>
    <p><input type="checkbox" name="classes" value="true"> Ближайшие занятия</p>
    <p><input type="submit" value="Сохранить"></p>
    </form>
    </div>
</div>
            <div class="panel panel-default">
            <div class="panel-heading">Изменить пароль</div>
            <div>
            <div class="well">
            <form:form method="POST" action="">
                <div>
                    <input type="text" size="15" id="passwordOld"><label style="margin-left: 5pt; font-weight: normal" for="passwordOld">Текущий пароль*</label>
                </div>
                <div>
                    <input type="text" size="15" id="passwordNew"><label style="margin-left: 5pt; font-weight: normal" for="passwordNew">Новый пароль*</label>
                </div>
                <div>
                    <input type="text" size="15" id="passwordConfirm"><label style="margin-left: 5pt; font-weight: normal" for="passwordConfirm">Подтверждение*</label>
                </div>
                <p><input type="submit" value="Сохранить"></p>
            </form:form>
            </div>
            </div>
            </div>
<%@ include file="footer.jsp"%>