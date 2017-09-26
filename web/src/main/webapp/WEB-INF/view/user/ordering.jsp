<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <body>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="title" var="i18n"/>
        <div class="page-wrapper">
                <div id="page-content">  
                    <h1><fmt:message bundle="${i18n}"  key="ordering.title"/></h1>
                    <div id="back">
                        <form name="back" action="frontController?command=car" method="POST">
                            <input type="submit" value="<fmt:message bundle="${i18n}" key="ordering.back.button"/>"/>
                        </form>
                    </div>
                    <br/>
                    <b> <fmt:message bundle="${i18n}" key="car.table.name.car"/> </b> <c:out value="${car.carName}"/> <br/>
                    <b> <fmt:message bundle="${i18n}" key="car.table.price"/> </b> <c:out value="${car.pricePerDay}"/><br/>
                    <b> <img src="${car.image}" width="200" height="135"/></b> <br/>
                    <input type="hidden" name="carId" value="${car.carId}" />
                    <form name ="order"  method="POST" action="frontController?command=Ordering">
                        <p><fmt:message bundle="${i18n}"  key="ordering.start.date"/></p>
                        <jsp:useBean id="now" class="java.util.Date" scope="page"/>
                        <input type="date" name="date" value="" required  max="2020-12-31" min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>"/>
                        <div class="notice"><fmt:message bundle="${i18n}"  key="ordering.period"/></div>
                        <input  type="number"  min="1" max="30" required name="period" value="" placeholder="" />
                        <input type="submit" value="<fmt:message bundle="${i18n}"  key="car.order"/>" />
                    </form>
                     </div>
                </div>
            </div>
    </body>

