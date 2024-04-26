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
            <form action="register" method="post" class="form" id="form-1">
                <h3 class="heading">Welcome</h3>

                <div class="form-group">
                    <label for="user" class="form-label">UserName</label>
                    <input type="text" class="form-control" id="user" name="user" placeholder="UserName" value="${user}" required>
                    <span class="form-message">${errUserName}</span>
                </div>
                <div class="form-group">
                    <label for="name" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Full Name" value="${name}" required>
                    <span class="form-message">${errFullName}</span>
                </div>
                <div class="form-group">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${email}" required>
                    <span class="form-message">${errEmail}</span>
                </div>
                <div class="form-group">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="number" class="form-control" id="phone" name="phone" placeholder="Phone" min="0"  value="${phone}" required>
                    <span class="form-message">${errPhone}</span>
                </div>
                <div class="form-group">
                    <label for="dob" class="form-label">Birthday</label>
                    <input type="date" class="form-control" id="dob" name="dob" value="${dob}" required>
                    <span class="form-message">${errBirthday}</span>
                </div>

                    <div style="display: flex;gap: 10px;justify-content: center">
                        
                    <div style="display: flex;gap: 10px;justify-content: center; align-items: center">
                        <input type="radio" class="form-control" id="male" name="gender" value="1" checked required>
                        <label for="male" class="form-label">Male</label>
                    </div>
                    <div style="display: flex;gap: 10px;justify-content: center; align-items: center">
                        <input type="radio" class="form-control" id="female" name="gender" value="0" required>
                        <label for="female" class="form-label">Female</label> 
                    </div>
                    <span class="form-message"></span>
                    
                </div>
                <div class="form-group">
                    <label for="pass" class="form-label">Password</label>
                    <input type="password" class="form-control" id="pass" name="pass" placeholder="Password" required>
                    <span class="form-message">${errPassword}</span>
                </div>

                <div class="form-group">
                    <label for="cpass" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="cpass" name="cpass" placeholder="Confirm password" required>
                    <span class="form-message"><${errPassword}/span>
                </div>
                <div>
                    <p style="color: red; margin-bottom: 10px;">${error}</p>
                </div>

                <div class="form-show">
                    <input type="checkbox" id="show" > <p>Show password</p> 
                </div>
                <div>
                    <button type="submit" class="form-submit">Register</button>
                </div>

                <div>
                    <p class="desc">Login with your account!</p>
                    <button type="button" class="form-submit" id="login" style="width: 60%;">Login</button>
                </div>

            </form>
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
