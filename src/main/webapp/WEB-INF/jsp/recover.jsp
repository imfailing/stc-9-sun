<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
   <c:if test= "${not empty param.error}">
            <br>По указанному e-mail направлена ссылка на восстановление
   </c:if>
    <form:form method="POST" action="" modelAttribute="user">
        <form:label path="email">E-mail *</form:label>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error"/>
        <input type= "submit" value= "Восстановить пароль">
    </form:form>
<%@include file="footer.jsp" %>
