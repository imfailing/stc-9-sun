<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">${lesson.title}</div>
    <!-- Таблица -->
    <table class="table">
        <form:form method="post" action="" modelAttribute="lesson" cssClass="form-inline">
            <form:input path="id" hidden="true"/>
            <tr>
                <td>Тема</td>
                <td>${lesson.title}</td>
                <td>
                    <c:if test="${lesson.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeTitle" aria-expanded="false" aria-controls="changeTitle">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${lesson.id != 0}"> class="collapse" id="changeTitle" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="title" placeholder="Введите тему занятия" cssClass="form-control"/>
                                <form:errors path="title" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Описание</td>
                <td>${lesson.description}</td>
                <td>
                    <c:if test="${lesson.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeDescription" aria-expanded="false" aria-controls="changeDescription">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${lesson.id != 0}"> class="collapse" id="changeDescription" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="description" placeholder="Введите описание занятия" cssClass="form-control"/>
                                <form:errors path="description" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Дата</td>
                <td>${lesson.date}</td>
                <td>
                    <c:if test="${lesson.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeStart" aria-expanded="false" aria-controls="changeStart">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${lesson.id != 0}"> class="collapse" id="changeStart" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input type="date" path="date" placeholder="Введите дату занятия" cssClass="form-control"/>
                                <form:errors path="date" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <c:if test="${lesson.id != 0}">
                        <a role="button" class="btn btn-danger" href="/group/${groupId}/lessons/delete/${lesson.id}">
                            Удалить
                        </a>
                    </c:if>
                </td>
            </tr>
        </form:form>
    </table>
</div>

<%@include file="footer.jsp" %>
