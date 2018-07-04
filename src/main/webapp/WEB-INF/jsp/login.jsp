<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
    <sec:authorize access="isAnonymous()">
        <c:if test= "${empty param.error}">
            Вы не вошли в систему
        </c:if>
        <c:if test= "${not empty param.error}">
            <br>Неправильный логин или пароль
        </c:if>
        <form:form name= "form" action= "/j_spring_security_check" method= "post">

            <label for= "inputEmail" class= ""></label>
            <input id= "inputEmail" class= "" name= "j_username" required autofocus/>

            <label for= "inputPassword" class= ""></label>
            <input type= "password" id= "inputPassword" class= "" name= "j_password" required/>
            <input type= "submit" value= "Войти">
        </form:form>
        <a href="/recover">Восстановить пароль</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        Вы вошли как: <sec:authentication property= "principal.firstName"/> с ролью: <sec:authentication property= "principal.roles"/>
    </sec:authorize>
<%@include file="footer.jsp" %>
