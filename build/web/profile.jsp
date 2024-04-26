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
            <form action="profile" method="post">
                <table style="margin: 10px">
                    <tr>
                        <td><label for="name" >Full Name</label></td>
                        <td><input type="text" id="name" name="name" placeholder="Name" value="${sessionScope.acc.getName()}" required></td>
                    </tr>
                    <tr>
                        <td><label for="email" >Email</label></td>
                        <td><input type="email" id="email" name="email" value="${sessionScope.acc.getEmail()}" required></td>
                    </tr>
                    <tr>
                        <td><label for="phone">Phone</label></td>
                        <td><input type="number" id="phone" name="phone" value="${sessionScope.acc.getPhone()}" required></td>
                    </tr>
                    <tr>
                        <td><label for="dob">Birthday</label></td>
                        <td><input type="date" id="dob" name="dob" value="${sessionScope.acc.getDob()}" required></td>
                    </tr>
                    <tr>
                        <td><label>Gender</label></td>
                        <td>
                            <input type="radio" id="male" name="gender" value="1" ${sessionScope.acc.getGender() == '1' ? 'checked':''}>
                            <label for="male">Male</label>
                            <input type="radio" id="female" name="gender" value="0"  ${sessionScope.acc.getGender() == '0' ? 'checked':''}>
                            <label for="female">Female</label>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update" style="background-color: #007bff"></td>
                    </tr>
                </table>
            </form>
            <a href="password">Change password>></a>
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
