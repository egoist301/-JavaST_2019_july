<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          rel="stylesheet"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
          crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="css/mdb.min.css" rel="stylesheet">

    <title>Profile</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top scrolling-navbar">
        <div class="container">
            <a href="index.jsp" class="navbar-brand">Rubiks</a>
        </div>
    </nav>
    <div id="intro" class="view">
        <div class="mask rgba-black-strong">
            <div class="container-fluid d-flex align-items-center justify-content-center h-100">
                <div class="row d-flex justify-content-center text-center">
                    <div class="col-md-10">
                        <h2 class="display-4 font-weight-bold white-text pt-5 mb-2">
                            Rubiks</h2>
                        <button class="btn btn-outline-white waves-effect waves-light">
                            Read More <em class="fa fa-book"></em></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<hr class="my-5">
<!-- Form to log in-->
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top">User profile:</h2>
        <form>
            <div class="form-group">
                <label for="exampleName">Username</label>
                <input type="text" class="form-control" id="exampleName"
                       placeholder="user" required disabled>
            </div>
            <div class="form-group">
                <label for="exampleRole">Role</label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleRole" placeholder="role" required disabled>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Old password</label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" placeholder="Old password"
                       required>
            </div>
            <div class="form-group">
                <label for="exampleMail">Email</label>
                <input type="email" class="form-control" id="exampleMail"
                       placeholder="email" required disabled>
            </div>
            <div class="form-group">
                <label for="examplePhone">Phone</label>
                <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}"
                       class="form-control" id="examplePhone"
                       placeholder="phone" required disabled>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">New password</label>
                <input type="password" id="password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword2" placeholder="New password"
                       required onkeyup='check();'>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword3">New password again</label>
                <input type="password" id="confirm_password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword3" placeholder="New password"
                       required onkeyup='check();'>
                <span id="message"></span>
            </div>
            <button type="submit" class="btn btn-primary" id="submit"
                    disabled="">Change password
            </button>
            <button type="reset" class="btn btn-primary">Clear</button>
        </form>
    </div>
</div>
<hr class="my-5">

<!-- Footer -->
<footer>
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="footer-left">
                <a href="#">Terms and conditions</a>
                <a href="#">Privacy</a>
                <p class="pull-left">Copyright © 2019</p>
            </div>
            <div class="footer-right">
                <span>Follow us:</span>
                <a href="#"><em class="fab fa-facebook-f"></em></a>
                <a href="#"><em class="fab fa-twitter"></em></a>
                <a href="#"><em class="fab fa-instagram"></em></a>
            </div>
        </div>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/mdb.min.js"></script>
<script src="js/mdb.js"></script>
<script src="js/main.js"></script>
<!--
<script src="../web/js/bootstrap-validate.js"></script>
<script>
    bootstrapValidate('#exampleName', 'max:10:Please do not enter more than 10 characters!')
</script>-->
</body>
</html>