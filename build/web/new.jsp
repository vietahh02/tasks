<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="icon" href="img/logo.png" type="image/gif" />
        <title>Job</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/font-awesome/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="home">
            <form action="new" method="post">
                <table style="margin: 10px">
                    <tr>
                        <td><label for="name" >Name</label></td>
                        <td><input type="text" id="name" name="name" value="${name}" placeholder="Name" required></td>
                    </tr>
                    <tr>
                        <td><label for="date" >Date</label></td>
                        <td><input type="date" id="date" name="date" required></td>
                    </tr>
                    <tr>
                        <td><label for="d" >Time</label></td>
                        <td><input type="time" id="time" name="time" required></td>
                    </tr>
                    <tr>
                        <td><label for="des">Description</label></td>
                        <td><textarea id="des" name="des" rows="5" cols="10" style="width: 250px;max-height: 300px" required>${des}</textarea></td>
                    </tr>
                    <tr>
                        <td><label for="pri">Prioritize</label></td>
                        <td>
                            <div class="rating">
                                <input type="radio" id="star5" name="rating" value="5" /><label for="star5"></label>
                                <input type="radio" id="star4" name="rating" value="4" /><label for="star4"></label>
                                <input type="radio" id="star3" name="rating" value="3" /><label for="star3"></label>
                                <input type="radio" id="star2" name="rating" value="2" /><label for="star2"></label>
                                <input type="radio" id="star1" name="rating" value="1" checked/><label for="star1"></label>
                            </div>  
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Create"></td>
                    </tr>

                </table>
            </form>
        </div>
        <div id="toast"></div>
        <script src="./js/toast.js"></script>
        <script>
            suc = "${suc}";
            err = "${err}";
            if (suc !== "null" && suc !== "") {
                success(suc);
            }
            if (err !== "null" && err !== "") {
                error(err);
            }
            console.log(suc, err);
        </script>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
