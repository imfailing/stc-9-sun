<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<c:if test= "${not empty param.error}">
    Пользователь с таким e-mail уже существует.
</c:if>
<form:form method="POST" action="" modelAttribute="user">
    <div>
        <form:label path="lastName">Фамилия *</form:label>
        <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error"/>
    </div>
    <div>
        <form:label path="firstName">Имя *</form:label>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error"/>
    </div>
    <div>
        <form:label path="patronymic">Отчество *</form:label>
        <form:input path="patronymic"/>
        <form:errors path="patronymic" cssClass="error"/>
    </div>
    <div>
        <form:label path="email">E-mail *</form:label>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error"/>
    </div>
    <div>
        <form:label path="password">Пароль *</form:label>
        <form:input path="password" type="password"/>
        <form:errors path="password" cssClass="error"/>
    </div>
    <input type="submit" value="Отправить"/>
</form:form>
<%@include file="footer.jsp" %>
