<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">${group.title}</div>
    <!-- Таблица -->
    <table class="table">
        <form:form method="post" action="" modelAttribute="group" cssClass="form-inline">
            <form:input path="id" hidden="true"/>
            <tr>
                <td>Название</td>
                <td>${group.title}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeTitle" aria-expanded="false" aria-controls="changeTitle">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeTitle" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="title" placeholder="Введите название" cssClass="form-control"/>
                                <form:errors path="title" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Описание</td>
                <td>${group.description}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeDescription" aria-expanded="false" aria-controls="changeDescription">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeDescription" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="description" placeholder="Введите описание" cssClass="form-control"/>
                                <form:errors path="description" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Дата начала</td>
                <td>${group.startDate}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeStart" aria-expanded="false" aria-controls="changeStart">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeStart" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input type="date" path="startDate" placeholder="Введите дату начала" cssClass="form-control"/>
                                <form:errors path="startDate" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Дата завершения</td>
                <td>${group.finishedDate}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeFinish" aria-expanded="false" aria-controls="changeFinish">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeFinish" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input type="date" path="finishedDate" placeholder="Введите дату завершения" cssClass="form-control"/>
                                <form:errors path="finishedDate" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <c:if test="${group.id != 0}">
                        <a role="button" class="btn btn-danger" href="/group/del/${group.id}">
                            Удалить
                        </a>
                        <a role="button" class="btn btn-default" href="/group/${group.id}/members">
                            Изменить состав группы
                        </a>
                        <a role="button" class="btn btn-default" href="/group/${group.id}/lessons/add">
                            Добавить занятие
                        </a>
                    </c:if>
                </td>
            </tr>
        </form:form>
    </table>
</div>

<c:if test="${group.id != 0}">
    <div class="panel panel-default">
        <!-- Содержание панели по умолчанию -->
        <div class="panel-heading">Занятия</div>
        <!-- Таблица -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
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
                    <td><a href="/group/${group.id}/lessons/${lesson.id}">${lesson.date}</a></td>
                    <td>${lesson.title}</td>
                    <td>${lesson.description}</td>
                    <td><a href="/group/${group.id}/lessons/delete/${lesson.id}">Удалить</a></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <div class="panel panel-default">
        <!-- Содержание панели по умолчанию -->
        <div class="panel-heading">Состав</div>
        <!-- Таблица -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>ФИО</th>
                <th>E-Mail</th>
            </tr>
            </thead>
            <c:set var="number" value="0"/>
            <c:forEach items="${members}" var="member">
                <tbody>
                <tr>
                    <td>${number=number+1}</td>
                    <td><a href="/users/${member.id}/">${member.fullName}</a></td>
                    <td>${member.email}</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
</c:if>

<%@include file="footer.jsp" %>