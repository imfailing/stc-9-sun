<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">${lesson.date} ${lesson.title}</div>
    <!-- Таблица -->
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Студент</th>
            <th>Оценка</th>
            <th>Посещение</th>
        </tr>
        </thead>
        <c:set var="number" value="0"/>
        <c:forEach items="${marks}" var="entity">
            <tbody>
            <tr>
                <td>${number=number+1}</td>
                <td><a href="/users/${entity.key.id}/">${entity.key.fullName}</a></td>
                <c:choose>
                    <c:when test="${entity.value eq null}">
                        <td>Нет оценки</td>
                        <td>Присутствовал</td>
                    </c:when>
                    <c:when test="${entity.value.value eq 0}">
                        <td>Нет оценки</td>
                        <td>Отсутствовал</td>
                    </c:when>
                    <c:otherwise>
                        <td>${entity.value.value}</td>
                        <td>Присутствовал</td>
                    </c:otherwise>
                </c:choose>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <form:form method="post" action="" modelAttribute="mark" cssClass="form-inline cst-form">
        <form:input path="id" hidden="true"/>
        <form:input path="lessonId" value="${lesson.id}" hidden="true"/>
        <div class="input-group">
            <span class="input-group-addon" id="user-describe">Выберите студента:</span>
            <form:select path="userId" cssClass="form-control" aria-describedby="user-describe">
                <c:forEach items="${marks}" var="entry">
                    <form:option value="${entry.key.id}">
                        ${entry.key.fullName}
                    </form:option>
                </c:forEach>
            </form:select>
        </div>
        <form:errors path="userId" cssClass="alert alert-danger custom-alert" role="alert"/>
        <div class="input-group">
            <span class="input-group-addon" id="value-describe">Оценка. Если студент не был на занятии, укажтите 0:</span>
            <form:input path="value" cssClass="form-control" type="number" value="1" min="0" max="100" aria-describedby="value-describe"/>
        </div>
        <form:errors path="value" cssClass="alert alert-danger custom-alert" role="alert"/>
        <button type="submit" class="btn btn-success">Выставить оценку</button>
    </form:form>
</div>
<%@include file="footer.jsp" %>