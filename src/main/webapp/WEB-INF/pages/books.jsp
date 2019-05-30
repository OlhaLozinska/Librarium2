<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>

<html>
<head>
    <title>Books</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jQueryValidator.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/w3.css">
    <style>
        #searchLine {
            padding: 20px;
        }
        #configurator {
            margin: 20px;
            padding: 20px;
            border: 1px solid rgba(111, 111, 111, 0.76);
            border-radius: 5px;
        }
    </style>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/resources/images/wallpaper.jpg');">

<div id="header">
    <%-- include header and navigation bar --%>
    <jsp:include page="/WEB-INF/header.jsp"></jsp:include>
</div>

<div class="container-fluid">

    <div class="row" style="margin-top: 40px;" id="configurator">
        <div class="col-md-2">
            <input class="form-control" id="searchLine" type="text" placeholder="Search...">
        </div>
        <div class="col-md-8 offset-2">
            <form method="post" class="form-inline needs-validation" action="${pageContext.request.contextPath}/books" novalidate>
                <label for="startDate" class="mr-sm-2">From:</label>
                <input type="date" class="form-control mb-2 mr-sm-2" value="${startDate}" id="startDate" name="startDate" required>
                <label for="endDate" class="mr-sm-2">To:</label>
                <input type="date" class="form-control mb-2 mr-sm-2" value="${endDate}" id="endDate" name="endDate" required>
                <div class="form-check mb-2 mr-sm-2">
                    <label class="form-check-label">
                        <input class="form-check-input" type="checkbox" id="unpopularFirst" name="unpopularFirst" value="unpopularFirst"> Unpopular first
                    </label>
                </div>
                <button type="submit" class="btn btn-outline-success mb-2">Sort by rating</button>
                <a href="${pageContext.request.contextPath}/books" class="btn btn-outline-danger mb-2" style="margin-left: 20px;">Reset</a>
            </form>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${books}" var="book">
            <div class = "col-md-3 d-flex" id="booksCards">
                <div class="card flex-fill">
                    <img class="card-img-top img-fluid" src="${pageContext.request.contextPath}/resources/images/${book.imageUrl}.jpg" alt="${book.name} image">
                    <div class="card-body">
                        <h4 class="card-title"><c:out value="${book.name}"/></h4>
                        <p class="card-text">by
                            <c:forEach items="${book.authors}" var="author">
                                <c:out value="${author.firstName} ${author.lastName}"/>
                            </c:forEach>
                        </p>
                    </div>
                    <div class="card-footer">
                        <strong>Rating: </strong><c:out value="${book.rating}"/> / 100
                        <a href="${pageContext.request.contextPath}/book/${book.id}" style="float:right" class="btn btn-primary stretched-link">See detailed info</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty books}">
        <div class="container-fluid">
            <div class="row">
                <div class = "col-md-12" >
                    <h2 class="text-center">There are no books ordered in that period</h2>
                </div>
            </div>
        </div>
    </c:if>
</div>

<script>
    $(document).ready(function(){
        $("#searchLine").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#booksCards div:first-child").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
        <c:if test="${not empty unpopularFirst}">
        $("#unpopularFirst").prop('checked', true);
        </c:if>
    });
</script>

<%-- include footer --%>
<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
</body>
</html>

<%--<html>--%>
<%--<head>--%>
    <%--<title>Books</title>--%>

<%--</head>--%>
<%--<body>--%>


        <%--<c:forEach items="${books}" var="book">--%>
            <%--<p>--%>
                <%--${book.id} ${book.createdAt} ${book.name} ${book.description} ${book.pageQuantity}--%>
            <%--</p>--%>
            <%--<c:forEach items="${book.authors}" var="author">--%>
                <%--<p>--%>
                    <%--${author.firstName} ${author.lastName}--%>
                <%--</p>--%>
                <%--</c:forEach>--%>
        <%--</c:forEach>--%>


    <%--<c:if test="${empty books}">--%>

                    <%--<h2 >There are no books ordered in that period</h2>--%>

    <%--</c:if>--%>



<%--</body>--%>
<%--</html>--%>
