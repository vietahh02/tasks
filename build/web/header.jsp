<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Job</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/font-awesome/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/toast.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    </head>
    <body>
        <div class="header">

            <div class="head1" style="background: black">
                <div class="logo" style="cursor: pointer;">
                    <img src="img/logo.png" alt="">
                </div>
                
                <div class="info">
                    <c:if test="${sessionScope.acc == null}">
                        <div class="login">Log In</div>
                        <div class="register">Register</div>
                    </c:if>
                    <c:if test="${sessionScope.acc != null}">
                        <div class="profile">${sessionScope.acc.getA().getUser()}</div>
                        <!--<div class="profile">Profile</div>-->
                        <div class="logout">Log Out</div>
                    </c:if>

                </div>
            </div>

            <div class="head2">
                <div class="search">
                    <input type="text" placeholder="Search..." id="search">
                    <button id="go"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>

        </div>
        
        <script>
            $("#go").click(function (){
                window.location.href = "home?search="+$("#search").val();
            });
            $(".login").click(()=>{
                window.location.href = "login";
            });
            $(".register").click(()=>{
                window.location.href = "register";
            });
            $(".logout").click(()=> {
                window.location.href = "login?logout=1";
            });
            $(".logo").click(function () {
                window.location.href = 'home';
            });
            $(".profile").click(function () {
                window.location.href = 'profile';
            });
        </script>
    </body>
</html>