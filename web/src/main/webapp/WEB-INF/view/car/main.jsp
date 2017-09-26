<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="title" var="i18n"/>
</div>
<div class="container-fluid">

        <table class="table">
            <tr>
                <div class="col-md-12">  </h1>  </div>
                <th class="col-md-2"><fmt:message bundle="${i18n}" key="car.table.name.car"/></th>
                <div class="col-md-10">
                    <th class="col-md-4"><fmt:message bundle="${i18n}" key="car.table.price"></fmt:message> </th>
                    <th class="col-md-4"><fmt:message bundle="${i18n}" key="car.table.imj"/></th>
                    <th class="col-md-2"></th>
                </div>
            </tr>

            <c:forEach var="car" items="${carForUser}" varStatus="status">
                <tr class="info">
                    <td class="col-md-2">${car.carName}</td>
                    <div class="col-md-10">
                        <td class="col-md-4">${car.pricePerDay}</td>
                        <td class="col-md-4"> <img src="${car.image}" width="200" height="135"/></td>



                        <td class="col-md-2">
                            <c:if test="${not empty user}">
                            <form action="frontController?command=orderingRE" method="post" >
                            <input type="hidden" name="carId" value="${car.carId}" />
                         <%--   <input type="hidden" name="carName" value="${car.carName}" />
                            <input type="hidden" name="carPrice" value="${car.pricePerDay}" />
                            <input type="hidden" name="carImage" value="${car.image}" />--%>
                            <input type="submit" value="<fmt:message bundle="${i18n}" key="car.order"/>" />
                        </form>
                            </c:if>
                        </td>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>








