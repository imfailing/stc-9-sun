<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<form:form method="POST" action="/changepass" modelAttribute="userDTO">
    <form:hidden path="id"></form:hidden>
    <form:hidden path="active"></form:hidden>
    <form:hidden path="email"></form:hidden>
    <form:hidden path="firstName"></form:hidden>
    <form:hidden path="lastName"></form:hidden>
    <form:hidden path="patronymic"></form:hidden>
    <form:label path="password">Пароль *</form:label>
    <form:input path="password"/>
    <form:errors path="password" cssClass="error"/>
    <p><input type="submit" value="Сменить пароль"></p>
</form:form>
<%@include file="footer.jsp" %>
