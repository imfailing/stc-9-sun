<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp"%>
<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">${user.fullName}</div>
    <!-- Таблица -->
    <table class="table">
        <form:form method="post" action="" modelAttribute="user" cssClass="form-inline">
            <form:input path="id" hidden="true"/>
            <tr>
                <td>Фамилия</td>
                <td>${user.lastName}</td>
                <td>
                    <a role="button" data-toggle="collapse" href="#changeLastName" aria-expanded="false" aria-controls="changeLastName">
                        Изменить
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div class="collapse" id="changeLastName">
                        <div class="well">
                            <div class="form-group">
                                <form:input path="lastName" placeholder="Введите фамилию" cssClass="form-control"/>
                                <form:errors path="lastName" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${user.firstName}</td>
                <td>
                    <a role="button" data-toggle="collapse" href="#changeFirstName" aria-expanded="false" aria-controls="changeFirstName">
                        Изменить
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div class="collapse" id="changeFirstName">
                        <div class="well">
                            <div class="form-group">
                                <form:input path="firstName" placeholder="Введите имя" cssClass="form-control"/>
                                <form:errors path="firstName" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Отчество</td>
                <td>${user.patronymic}</td>
                <td>
                    <a role="button" data-toggle="collapse" href="#changePatronymic" aria-expanded="false" aria-controls="changePatronymic">
                        Изменить
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div class="collapse" id="changePatronymic">
                        <div class="well">
                            <div class="form-group">
                                <form:input path="patronymic" placeholder="Введите отчество" cssClass="form-control"/>
                                <form:errors path="patronymic" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>E-Mail</td>
                <td>${user.email}</td>
                <td>
                    <a role="button" data-toggle="collapse" href="#changeEmail" aria-expanded="false" aria-controls="changeEmail">
                        Изменить
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div class="collapse" id="changeEmail">
                        <div class="well">
                            <div class="form-group">
                                <form:input path="email" placeholder="Введите E-Mail" cssClass="form-control"/>
                                <form:errors path="email" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <button type="submit" class="btn btn-success">Соханить</button>
                </td>
            </tr>
        </form:form>
    </table>
</div>
<%@ include file="footer.jsp"%>