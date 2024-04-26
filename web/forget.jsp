<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="icon" href="img/logo.png" type="image/gif" />
        <title>Job</title>
        <link rel="stylesheet" href="./css/form.css"/>
        <link rel="stylesheet" href="./css/font-awesome/css/all.min.css"/>
        <link rel="stylesheet" href="css/toast.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    </head>
    <body>
        <div class="main">
            <c:if test="${email == null && phone == null}">
                <form action="forget" method="post" class="form" id="form-1">
                    <h3 class="heading">Enter email and phone</h3>
                    <div class="form-group">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${email}" required>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="number" class="form-control" id="phone" name="phone" placeholder="Phone" min="0"  value="${phone}" required>
                        <span class="form-message"></span>
                    </div>
                    <div>
                        <button type="submit" class="form-submit">Send</button>
                    </div>
                </form>
            </c:if>
            <c:if test="${email != null && phone != null}">
                <form action="forget?email=${email}&phone=${phone}" method="post" class="form" id="form-1">
                    <h3 class="heading">Change Password</h3>
                    <div class="form-group">
                        <label for="pass" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="pass" name="pass" placeholder="Password" required>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <label for="cpass" class="form-label">Confirm Password</label>
                        <input type="password" class="form-control" id="cpass" name="cpass" placeholder="Confirm password" required>
                        <span class="form-message"></span>
                    </div>
                    <div>
                        <button type="submit" class="form-submit">Send</button>
                    </div>
                </form>
            </c:if>

        </div>
        <div id="toast"></div>
        <script src="./js/toast.js"></script>
        <script src="./js/form.js"></script>
        <script>
            suc = "${suc}";
            err = "${err}";
            if (suc !== "null" && suc !== "") {
                success(suc);
            }
            if (err !== "null" && err !== "") {
                error(err);
            }

            $('#login').click(function () {
                window.location.href = 'login';
            });

            $('#show').click(function () {
                if ($(this).is(':checked') && $('#pass').attr('type') === 'password') {
                    $('#pass').attr('type', 'text');
                    $('#cpass').attr('type', 'text');
                } else {
                    $('#pass').attr('type', 'password');
                    $('#cpass').attr('type', 'password');
                }
            });
        </script>

    </body>
</html>
