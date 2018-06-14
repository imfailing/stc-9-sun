<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">Группы</div>
    <!-- Таблица -->
    <table class="table">
        <tr class="custom-tr">
            <div cssClass="form-inline">
                <td>
                    <a href="/group"><button type="submit" class="btn btn-success">+</button></a>
                </td>
                <td>
                </td>
            </div>
        </tr>
        <tr>
            <td>Название</td>
            <td>Дата начала</td>
            <td>Дата конца</td>
        </tr>
        <c:forEach items="${groups}" var="group">
            <tr>
                <td>
                    <a href="/group/${group.id}/">
                            ${group.title}
                    </a>
                </td>
                <td>${group.start_date}</td>
                <td>${group.finished_date}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp" %>
