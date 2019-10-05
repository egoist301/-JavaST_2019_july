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

    <title>Logging</title>
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
                        <!--<button
                                class="btn btn-outline-white waves-effect waves-light">
                            Read More <em class="fa fa-book"></em>
                        </button>-->
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
        <h2 class="text-center h2-pad-top">Login:</h2>
        <form>
            <div class="form-group">
                <label for="exampleName">Name</label>
                <input type="text" class="form-control form-control-sm"
                       id="exampleName" aria-describedby="nameHelp"
                       placeholder="Enter your name" required maxlength="10">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" placeholder="Password"
                       required>
            </div>
            <!--<div class="g-recaptcha"
                 data-sitekey="6KepjAsTFFFFFFMqccY0ZiGqc3TEd3YVxo8cHsGX
                 "></div>-->
            <button type="submit" class="btn btn-primary">LOG IN</button>
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
<!-- <script src='https://www.google.com/recaptcha/api.js'></script>
<script src="../web/js/bootstrap-validate.js"></script>
<script>
    bootstrapValidate('#exampleName', 'max:10:Please do not enter more than 10 characters!')
</script>-->
</body>
</html>