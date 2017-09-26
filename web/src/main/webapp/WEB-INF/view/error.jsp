<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="title" var="i18n"/>

</head>
<body>
<div>
    <br/>
    <br/>
    <h2><fmt:message bundle="${i18n}" key="err.title"/></h2><br/>
    <b> <fmt:message bundle="${i18n}" key="error.clarification"/> </b> <br/>
    <div id="back">
        <form name="BackForm" method="POST" action="/car/frontController?command=car">
            <input type="submit" value="<fmt:message bundle="${i18n}" key="error.back.button"/>"/>
        </form>
    </div>
</div>
</body>
</html>
