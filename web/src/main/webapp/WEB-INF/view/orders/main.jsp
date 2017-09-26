<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="title" var="i18n"/>

<body>

<div class="container">
    <h2><fmt:message bundle="${i18n}"  key="orders.title"/></h2>
    <table class="table">
<thead>
        <tr>
            <th><fmt:message bundle="${i18n}"  key="orders.orderId"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.date"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.user.name"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.car.name"/></th>
            <th><fmt:message bundle="${i18n}"  key="order.start.date"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.period"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.total"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.status"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.damages"/></th>
            <th><fmt:message bundle="${i18n}"  key="orders.refusal.reason"/></th>
        </tr>
        </thead>
        <tbody>
<c:forEach var="order" items="${orders}" varStatus="status">
    <c:if test="${order.status eq 'NEW'}">
        <tr class ="success">
    </c:if>
    <c:if test="${order.status eq 'APPROVED_BY'}">
        <tr class="info">
    </c:if>
    <c:if test="${order.status eq 'DENIED'}">
        <tr class="danger">
    </c:if>
    <c:if test="${order.status eq 'ARCHIVE'}">
        <tr>
    </c:if>
    <th>${order.orderId}</th>
    <th><fmt:formatDate pattern="yyyy-MM-dd" value="${order.date}" /></th>
    <th>${order.name}</th>
    <th>${order.carName}</th>
    <th><fmt:formatDate pattern="yyyy-MM-dd" value="${order.rentalStartDate}" /></th>
    <th>${order.period}</th>
    <th>${order.total}</th>
    <th>${order.status}</th>
    <th>${order.damages}</th>
    <th>${order.refusalReason}</th>
            <c:if test="${user.type eq 'ADMIN' and order.status eq 'NEW'}">
            <th>
                <form name ="ChangeCarInfoForm"  method="POST" action="frontController?command=changeOrderStatus" class="menu">
                    <input type="hidden" name="orderId" value="${order.orderId}" />
                    <input type="hidden" name="confirm" value="confirm" />
                    <input type="submit" value="<fmt:message bundle="${i18n}"  key="orders.confirm"/> " />
                </form>
            </th>
            <th>
                <form name ="ChangeCarInfoForm"  method="POST" action="frontController?command=changeOrderStatus" class="menu">
                    <fmt:message bundle="${i18n}"  key="orders.refuse.reason"/>
                    <input type="text" maxlength="100" name="reasonForRefusal" value="" required/>
                    <input type="hidden" name="confirm" value="refuse" />
                    <input type="hidden" name="orderId" value="${order.orderId}" />
                    <input type="submit" value="<fmt:message bundle="${i18n}"  key="orders.refuse"/> " />
                </form>
            </th>
            </c:if><c:if test="${user.type eq 'ADMIN' and (order.status eq 'APPROVED_BY' or order.status eq 'DENIED')}">
            <th>
                <form name ="ChangeCarInfoForm"  method="POST" action="frontController?command=changeOrderStatus" class="menu">
                    <input type="hidden" name="orderId" value="${order.orderId}" />
                    <input type="hidden" name="confirm" value="archive" />
                    <input type="submit" value="<fmt:message bundle="${i18n}"  key="order.archive"/> " />
                </form>
            </th>
            <th>
                <form name ="ChangeCarInfoForm"  method="POST" action="frontController?command=indicateDamage" class="menu">
                    <fmt:message bundle="${i18n}"  key="order.damage.message"/>
                    <input type="text" maxlength="100" name="damage" value="" required/>
                    <input type="hidden" name="orderId" value="${order.orderId}" />
                    <input type="submit" value="<fmt:message bundle="${i18n}"  key="orders.confirm"/> " />
                </form>
            </th>
            </c:if>
        </tr>
</c:forEach>
        </tbody>
    </table>
</div>

</body>
