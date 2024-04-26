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
            <form action="password" method="post">
                <table style="margin: 10px">
                    <tr>
                        <td><label for="old" >Old password</label></td>
                        <td><input type="password" id="old" name="old" placeholder="Old password" required></td>
                    </tr>
                    <tr>
                        <td><label for="new" >New password</label></td>
                        <td><input type="password" id="new" name="new" placeholder="New password" required></td>
                    </tr>
                    <tr>
                        <td><label for="agian" >Confirm password</label></td>
                        <td><input type="password" id="again" name="again" placeholder="Confirm password" required></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Change" style="background-color: #818182;color: white"></td>
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

            $(".remove").click(function () {
                id = $(this).attr("num");
                if (confirm("You want delete task id=" + id + "?")) {
                    window.location.href = "update?mode=1&id=" + id;
                }
            });

            ra = "${task.getPrioritize()}";
            a = document.querySelectorAll("input[name='rating']");
            for (i = 0; i < a.length; i++) {
                if (a[i].value === ra) {
                    a[i].checked = true;
                }
            }
        </script>


        <jsp:include page="footer.jsp"/>
    </body>
</html>
