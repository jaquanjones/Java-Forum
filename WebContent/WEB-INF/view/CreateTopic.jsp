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

    <title>Create Topic</title>
</head>
<body>
<div class="container">

    <%--    LOGOUT BUTTON (LOGIN NOT NEEDED; USER CAN ONLY ACCESS WHEN LOGGED IN)--%>
    <div class="d-flex justify-content-end mt-4 mx-0 ">
        <a class="text-decoration-none text-white" href="Logout">
            <button type="button" class="btn btn-secondary btn-lg py-auto px-auto font-weight-light">
                Logout
            </button>
        </a>
    </div>

    <%--  PATH/TITLE (BREADCRUMB) --%>
    <div class="my-4 mb-auto mx-auto text-center">
        <div class="display-4 mx-auto my-auto px-auto pt-4"><a class="text-decoration-none" href="DisplayForums">All
            Forums</a> >
            <a class="text-decoration-none"
               href="DisplayTopics?forumIndex=${currentForumIndex}">${currentForum.name}</a> > Create Topic
        </div>
    </div>

    <%-- CREATE TOPIC FORM--%>
    <div class="container mt-5 mb-4 mx-auto">
        <form name="CreateTopic" method="post">
            <div class="form-group">
                <label class="lead font-weight-normal" for="subject">Subject:</label>
                <input name="subject" class="form-control" type="text" id="subject"/>
            </div>
            <div class="form-group">
                <label class="lead font-weight-normal" for="content">Content:</label>
                <textarea cols="40" class="form-control" name="content" rows="5" id="content"></textarea>
            </div>
            <div class="container text-center">
                <input class="btn btn-lg btn-dark display-4 mx-auto my-auto px-3 py-2 font-weight-light" type="submit"
                       value="Submit">
            </div>
        </form>
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