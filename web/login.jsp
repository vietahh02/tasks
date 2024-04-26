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
            <form action="login" method="post" class="form" id="form-1">
                <h3 class="heading">Welcome</h3>

                <div class="form-group">
                    <label for="user" class="form-label">UserName</label>
                    <input type="text" class="form-control" id="user" name="user" placeholder="UserName" required>
                    <span class="form-message"></span>
                </div>

                <div class="form-group">
                    <label for="pass" class="form-label">Password</label>
                    <input type="password" class="form-control" id="pass" name="pass" placeholder="Password" required>
                    <span class="form-message"></span>
                </div>
                <div>
                    <p style="color: red; margin-bottom: 10px;">${error}</p>
                </div>

                <div class="form-show" style="justify-content: space-between">
                    <div style="display: flex;gap: 6px"><input type="checkbox" id="show"><p>Show password</p> </div>
                    <a href="forget">Forget password?</a>
                </div>
                <div>
                    <button type="submit" class="form-submit">Login</button>
                </div>

                <div>
                    <p class="desc">Don't have an account?</p>
                    <button type="button" class="form-submit" id="register" style="width: 60%;">Register</button>
                </div>

            </form>
        </div>
        <div id="toast"></div>
        <script src="./js/toast.js"></script>
        <script src="./js/form.js"></script>
        <script>
            suc = "${suc}";
            err = "${err}";
            if( suc !== "null" && suc !== "") {
                success(suc);
            }
            if( err !== "null" && err !== "") {
                error(err);
            }
            $('#register').click(function () {
                window.location.href = 'register';
            });

            $('#show').click(function () {
                if ($(this).is(':checked') && $('#pass').attr('type') === 'password') {
                    $('#pass').attr('type', 'text');
                } else {
                    $('#pass').attr('type', 'password');
                }
            });
        </script>

    </body>
</html>
