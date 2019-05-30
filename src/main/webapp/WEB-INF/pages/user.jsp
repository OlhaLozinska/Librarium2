<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>User</title>

</head>
<body>

<c:if test="${empty user}">
    <h2 >There are no user</h2>
</c:if>

<c:if test="${not empty user}">
    <p>
        ${user.id} ${user.firstName} ${user.lastName} ${user.userType}
    </p>
    <c:forEach items="${user.orders}" var="order">
        <p>
            ${order.id} ${order.takeDate}
        </p>
    </c:forEach>
</c:if>
</body>
</html>
