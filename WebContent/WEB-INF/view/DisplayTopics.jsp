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

    <title>All Topics</title>
</head>
<body>

<div class="container">

    <%-- LOGIN/LOGOUT BUTTON --%>
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

    <%-- FORUM PATH (BREADCRUMBS) --%>
    <div class="container my-4 mx-auto text-center">
        <div class="display-4 mx-auto my-auto px-3 py-2"><a class="text-decoration-none" href="DisplayForums">All
            Forums</a> > ${currentForum.name}
        </div>
    </div>


    <%-- TOPICS TABLE --%>
    <table class="table table-bordered table-hover mx-auto my-4">
        <thead class="thead-dark">
        <tr>
            <th class="lead text-center" scope="col">Topic</th>
            <th class="lead text-center" scope="col">Author</th>
            <th class="lead text-center" scope="col">Replies</th>
            <th class="lead text-center" scope="col">Last Post</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${topics}" var="c">
            <tr>
                <td class="lead text-center"><a href="DisplayReplies?topicIndex=${c.id}">${c.title}</a></td>
                <td class="lead text-center">${c.author}</td>
                <td class="lead text-center">${c.replyCount}</td>
                <td class="lead text-center">${c.timestampToString(c.lastPost)}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- CREATE TOPIC BUTTON --%>
    <c:choose>
        <c:when test="${isLoggedIn}">
            <div class="my-3 mx-0 ">
                <a class="text-decoration-none text-white" href="CreateTopic">
                    <button type="button"
                            class="btn btn-primary btn-lg btn-block py-auto px-auto font-weight-light">
                        Create Topic
                    </button>
                </a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="my-3 mx-0 ">
                <a class="text-decoration-none text-white" href="Login">
                    <button type="button"
                            class="btn btn-primary btn-lg btn-block py-auto px-auto font-weight-light">
                        Create Topic
                    </button>
                </a>
            </div>
        </c:otherwise>
    </c:choose>

</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
</body>
</html>