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
    <div id="page-content">

        <h2> <fmt:message bundle="${i18n}" key="admin.active.users"/></h2>
        <table id="usertable">
            <tr>
                            <td class="heading">
                                <b> <fmt:message bundle="${i18n}" key="admin.user.id"/> </b>
                            </td>
                            <td class="heading">
                                <b> <fmt:message bundle="${i18n}" key="admin.user.login"/> </b>
                            </td>
                            <td class="heading">
                                <b> <fmt:message bundle="${i18n}" key="admin.user.name"/> </b>
                            </td>
                            <td class="heading">
                                <b> <fmt:message bundle="${i18n}" key="admin.user.address"/></b>
                            </td> 
                            <td class="heading">
                                <b> <fmt:message bundle="${i18n}" key="admin.user.email"/></b>
                            </td> 

                        </tr>
                        <c:forEach var="user" items="${activeUser}" varStatus="status">
                            <tr>
                                <td>
                                    <c:out value="${user.id}"/>
                                </td>
                                <td>
                                    <c:out value="${user.login}"/>
                                </td>
                                <td>
                                    <c:out value="${user.name}"/>
                                </td>
                                <td>
                                    <c:out value="${user.address}"/>
                                </td> 
                                <td>
                                    <c:out value="${user.email}"/>
                                </td> 
                                <td>
                                   <form name ="Status"  method="POST" action="frontController?command=delete" class="change">
                                        <input type="hidden" name="userId" value="${user.id}" />
                                        <input type="submit" value="<fmt:message bundle="${i18n}" key="admin.delete.user"/> " />
                                    </form>
                                </td>
                            </tr>
                            <tr>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
    </body>
</html>
