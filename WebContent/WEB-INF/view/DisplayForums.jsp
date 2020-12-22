<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>All Forums</title>
</head>
<body>
<div class="container my-4 mx-auto">

    <%--    LOGIN/LOGOUT BUTTON--%>
    <c:choose>
        <c:when test="${isLoggedIn}">
            <div class="d-flex justify-content-end mt-4 mx-0 ">
                <a class="text-decoration-none text-white" href="Logout">
                    <button type="button" class="btn btn-secondary btn-lg py-auto px-auto font-weight-light">
                        Logout
                    </button>
                </a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="d-flex justify-content-end mt-4 mx-0 ">
                <a class="text-decoration-none text-white" href="Login">
                    <button type="button" class="btn btn-dark btn-lg btn-block py-auto px-auto font-weight-light">
                        Login
                    </button>
                </a>
            </div>
        </c:otherwise>
    </c:choose>

    <%-- TITLE --%>
    <h2 class="display-4 mx-auto my-auto px-3 py-2">All Forums</h2>

    <%-- FORUMS TABLE --%>
    <table class="table table-bordered table-hover mx-auto my-4">
        <thead class="thead-dark">
        <tr>
            <th class="lead" scope="col">Forum</th>
            <th class="lead text-center" scope="col">Topics</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${forums}" var="c">
            <tr>
                <td class="lead"><a href="DisplayTopics?forumIndex=${c.id}">${c.name}</a></td>
                <td class="lead" style="text-align: center;">${c.size}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- CREATE FORUM BUTTON --%>
    <c:choose>
        <c:when test="${isLoggedIn}">
            <div class="my-3 mx-0 ">
                <a class="text-decoration-none text-white" href="CreateForum">
                    <button type="button"
                            class="btn btn-primary btn-lg btn-block py-auto px-auto font-weight-light">
                        Create Forum
                    </button>
                </a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="my-3 mx-0 ">
                <a class="text-decoration-none text-white" href=Login>
                    <button type="button"
                            class="btn btn-primary btn-lg btn-block py-auto px-auto font-weight-light">
                        Create Forum
                    </button>
                </a>
            </div>
        </c:otherwise>
    </c:choose>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>