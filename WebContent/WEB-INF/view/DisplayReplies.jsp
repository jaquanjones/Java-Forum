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

    <title>All Responses</title>
</head>
<body>
<%--PICK CONTAINER TYPE--%>
<div class="container-fluid">

    <%-- LOGIN/LOGOUT BUTTON --%>
    <c:choose>
        <c:when test="${isLoggedIn}">
            <div class="d-flex justify-content-end mt-4 mr-3">
                <a class="text-decoration-none text-white" href="Logout">
                    <button type="button" class="btn btn-secondary btn-lg py-auto px-auto font-weight-light">
                        Logout
                    </button>
                </a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="d-flex justify-content-end mt-4 mr-3 ">
                <a class="text-decoration-none text-white" href="Login">
                    <button type="button" class="btn btn-dark btn-lg btn-block py-auto px-auto font-weight-light">
                        Login
                    </button>
                </a>
            </div>
        </c:otherwise>
    </c:choose>

    <%-- FORUM PATH (BREADCRUMBS) --%>
    <div class="container-fluid my-4 mx-auto text-center">
        <div class="container display-4 mx-auto my-auto px-3 py-2">
            <a class="text-decoration-none" href="DisplayForums">All Forums</a> >
            <a class="text-decoration-none" href="DisplayTopics?forumIndex=${currentForumIndex}">${currentForum.name}</a>
            > ${currentTopic.title}
        </div>
    </div>

    <%-- REPLIES TABLE --%>
    <table class="table table-bordered table-hover mx-auto my-4">
        <thead class="thead-dark">
        <tr>
            <th class="lead text-center" scope="col">Author</th>
            <th class="lead text-center" scope="col">Content</th>
            <th class="lead text-center" scope="col">Posted On</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${replies}" var="c">
            <tr>
                <td class="lead text-center">${c.author}</td>
                <td class="lead text-justify">${c.content}</td>
                <td class="lead text-center">${ c.postedOn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- POST REPLY TITLE --%>
        <hr>
    <div class="container my-3 mx-auto">
        <div class="container text-center">
            <div class="badge badge-pill badge-dark  mt-4">
                <div class="display-4 mx-auto my-auto px-3 py-1 text-center">Post Reply</div>
            </div>
        </div>

        <%-- REPLY FORM --%>
        <div class="container my-4 mx-auto">
            <form name="DisplayReplies" method="post">
                <div class="form-group">
                    <label class="lead font-weight-normal" for="content">Reply:</label>
                    <textarea cols="40" class="form-control" name="content" rows="4" id="content"></textarea>
                </div>
                <div class="container text-center">
                    <input class="btn btn-lg btn-primary display-4 mx-auto my-auto px-3 py-2 lead font-weight-light"
                           type="submit"
                           value="Post">
                </div>
            </form>
        </div>

    </div>

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