<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
    <form name= "form" action= "/j_spring_security_check" method= "post" class= "">
        <security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_USER')" var= "isUSer"/>
        <c:if test= "${not isUSer}">
            <c:if test= "${empty param.error}">
                Вы не вошли в систему
            </c:if>
            <c:if test= "${not empty param.error}">
                <br>Неправильный логин или пароль
            </c:if>
        </c:if>
        <c:if test= "${isUSer}">
            Вы вошли как: <security:authentication property= "principal.username"/> с ролью: <security:authentication property= "principal.authorities"/>
        </c:if>

        <label for= "inputEmail" class= ""></label>
        <input id= "inputEmail" class= "" name= "j_username" required autofocus/>

        <label for= "inputPassword" class= ""></label>
        <input type= "password" id= "inputPassword" class= "" name= "j_password" required/>
        <input type= "submit" value= "Войти">
    </form>
<%@include file="footer.jsp" %>
