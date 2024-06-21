<%-- 
    Document   : create
    Created on : Oct 8, 2022, 4:15:05 PM
    Author     : hd
--%>

<%@page import="user_watch.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="images/logo.jpg">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css">
        <title>Create User Page</title>
        <style><%@include file="/WEB-INF/style_1.css"%></style>
    </head>
    <body>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>

        <div class="box">
            <form class="form-signin" action="MainController" method="POST">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Input your information</h1>
                User ID<input type="text" name="userID" required=""/></br>
                <%= userError.getUserID()%></br>
                Full Name<input type="text" name="fullName" required=""/></br>
                <%= userError.getFullName()%></br>
                Role ID<input type="text" name="role" value="0" readonly=""/></br>
                <br>
                Password<input type="text" name="password" required=""/></br>
                <br>
                Confirm<input type="text" name="confirm" required=""/>
                <%= userError.getConfirm()%></br>
                <br>
                <button class="btn btn-success btn-block" type="submit" name="action" value="Create">
                    <i class="fas fa-sign-in-alt"></i> Create
                </button>
                <hr>
            </form>
        </div>
        <div class="footer"> 
            <jsp:include page="footer.html"/> 
        </div>
    </body>
</html>






