<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
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
    <div class="panel-heading">Изменить пароль</div>
    <div class="container">
        <form method="POST" action="">
            <div>
                <input type="password" name="passwordOld" id="passwordOld" pattern=".{7,25}"required title="7 to 25 characters"/>
                <label for="passwordOld" >Текущий пароль</label>
            </div>
            <div>
                <input type="password" pattern=".{7,25}"required title="7 to 25 characters" name="passwordNew" id="passwordNew"/>
                <label for="passwordNew" >Новый пароль</label>
            </div>
            <div>
                <input type="password" pattern=".{7,25}"required title="7 to 25 characters" name="passwordConfirm" id="passwordConfirm"/>
                <label for="passwordConfirm" >Подвердите пароль</label>
            </div>
            <input type="submit" value="Сменить пароль">
        </form>
        ${passResult}
    </div>

</div>
<%@include file="footer.jsp" %>