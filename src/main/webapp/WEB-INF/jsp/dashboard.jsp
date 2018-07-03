<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    Дашборд админа
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
    Дашборд юзера
</sec:authorize>
<%@include file="footer.jsp" %>
