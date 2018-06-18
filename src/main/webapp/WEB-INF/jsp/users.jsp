<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp"%>
<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">Пользователи</div>
    <!-- Таблица -->
    <table class="table">
        <tr class="custom-tr">
            <form:form method="post" action="" modelAttribute="user" cssClass="form-inline">
                <td>
                    <button type="submit" class="btn btn-success">+</button>
                </td>
                <td>
                    <div class="form-group">
                        <form:input path="lastName" placeholder="Введите фамилию" cssClass="form-control"/>
                        <form:errors path="lastName" cssClass="alert alert-danger custom-alert" role="alert"/>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <form:input path="firstName" placeholder="Введите имя" cssClass="form-control"/>
                        <form:errors path="firstName" cssClass="alert alert-danger custom-alert" role="alert"/>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <form:input path="patronymic" placeholder="Введите отчество" cssClass="form-control"/>
                        <form:errors path="patronymic" cssClass="alert alert-danger custom-alert" role="alert"/>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <form:input path="email" placeholder="Введите E-Mail" cssClass="form-control"/>
                        <form:errors path="email" cssClass="alert alert-danger custom-alert" role="alert"/>
                    </div>
                </td>
                <td>
                    <form:password path="password" placeholder="Введите пароль" cssClass="form-control"/>
                    <form:errors path="password" cssClass="alert alert-danger custom-alert" role="alert"/>
                </td>
            </form:form>
        </tr>
        <tr>
            <td>Id</td>
            <td colspan="3">ФИО</td>
            <td>E-Mail</td>
            <td>Активен</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td colspan="3">
                    <a href="/users/${user.id}/">
                    ${user.fullName}
                    </a>
                </td>
                <td>${user.email}</td>
                <td>${user.active}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="footer.jsp"%>