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
        <div class="error">

                    <br/>
                    <br/>
                    <h2><fmt:message bundle="${i18n}" key="error.auth"/> </h2><br/>
                    <b> <fmt:message bundle="${i18n}" key="error.auth.msg"/> </b> <br/>
                    <div id="back"> 
                        <form name ="BackForm"  method="POST" action="frontController?command=car" >
                            <input type="submit" value="<fmt:message bundle="${i18n}" key="error.back.button.joke"/>"/>
                        </form>
            </div> 
        </div>
    </body>
</html>
