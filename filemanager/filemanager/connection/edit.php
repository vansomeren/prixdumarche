<?php
/*
  EDIT.PHP
  Allows user to edit specific entry in database
 */

// creates the edit record form
// since this form is used multiple times in this file, I have made it a function that is easily reusable
function renderForm($id, $firstname, $lastname, $username, $password, $error) {
    ?>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
    <html>
        <head>
            <title>Edit Record</title>
        </head>

        <!DOCTYPE html>

        <html lang="en">
            <head>
                <meta charset="utf-8">
                <title>Sign in &middot; Twitter Bootstrap</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta name="description" content="">
                <meta name="author" content="">

                <!-- Le styles -->
                <link href="../css/bootstrap.css" rel="stylesheet">
                <style type="text/css">
                    body {
                        padding-top: 40px;
                        padding-bottom: 40px;
                        background-color: #f5f5f5;
                    }

                    .form-signin {
                        max-width: 300px;
                        padding: 19px 29px 29px;
                        margin: 0 auto 20px;
                        background-color: #fff;
                        border: 1px solid #e5e5e5;
                        -webkit-border-radius: 5px;
                        -moz-border-radius: 5px;
                        border-radius: 5px;
                        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                        -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                        box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    }
                    .form-signin .form-signin-heading,
                    .form-signin .checkbox {
                        margin-bottom: 10px;
                    }
                    .form-signin input[type="text"],
                    .form-signin input[type="password"] {
                        font-size: 16px;
                        height: auto;
                        margin-bottom: 15px;
                        padding: 7px 9px;
                    }

                </style>
                <link href="css/bootstrap-responsive.css" rel="stylesheet">

                <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
                <!--[if lt IE 9]>
                  <script src="../assets/js/html5shiv.js"></script>
                <![endif]-->

                <!-- Fav and touch icons -->
                <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
                <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
                <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                <link rel="shortcut icon" href="../assets/ico/favicon.png">
            </head>

            <body>
                <div class="navbar navbar-inverse navbar-fixed-top">
                    <div class="navbar-inner">
                        <div class="container">
                            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="brand" href="index.php">File Manager</a>
                            <div class="nav-collapse collapse">
                                <ul class="nav">
                                    <li class="active"><a href="#">Home</a></li>
                                    <li><a href="#about">About</a></li>
                                    <li><a href="#contact">Contact</a></li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Action</a></li>
                                            <li><a href="#">Another action</a></li>
                                            <li><a href="#">Something else here</a></li>
                                            <li class="divider"></li>
                                            <li class="nav-header">Nav header</li>
                                            <li><a href="#">Separated link</a></li>
                                            <li><a href="#">One more separated link</a></li>
                                        </ul>
                                    </li>
                                </ul>

                            </div><!--/.nav-collapse -->
                        </div>
                    </div>
                </div>


                <div class="container">
                    <form action="" method="post" class="form-signin">
                        <input type="hidden" name="id" value="<?php echo $id; ?>"/>
                        <div>
                            <p><strong>ID:</strong> <?php echo $id; ?></p>
                            <strong>First Name: *</strong> <input type="text" name="firstname" value="<?php echo $firstname; ?>"/><br/>
                            <strong>Last Name: *</strong> <input type="text" name="lastname" value="<?php echo $lastname; ?>"/><br/>
                            <strong>Username: *</strong> <input type="text" name="username" value="<?php echo $username; ?>"/><br/>
                            <strong>Password: *</strong> <input type="password" name="password" value="<?php echo $password; ?>"/><br/>
                            <p>* Required</p>
                            <input type="submit" name="submit" value="Submit">
                        </div>
                    </form>
            </body>
        </html>
        <?php
    }

// connect to the database
    include('connect.php');

// check if the form has been submitted. If it has, process the form and save it to the database
    if (isset($_POST['submit'])) {
        // confirm that the 'id' value is a valid integer before getting the form data
        if (is_numeric($_POST['id'])) {
            // get form data, making sure it is valid
            $id = $_POST['id'];
            $firstname = mysql_real_escape_string(htmlspecialchars($_POST['firstname']));
            $lastname = mysql_real_escape_string(htmlspecialchars($_POST['lastname']));
            $username = mysql_real_escape_string(htmlspecialchars($_POST['username']));
            $password = mysql_real_escape_string(htmlspecialchars($_POST['password']));

            // check that firstname/lastname fields are both filled in
            if ($firstname == '' || $lastname == '' || $username == '' || $password == '') {
                // generate error message
                $error = 'ERROR: Please fill in all required fields!';

                //error, display form
                renderForm($id, $firstname, $lastname, $username, $password, $error);
            } else {
                // save the data to the database
                mysql_query("UPDATE users SET firstname='$firstname', lastname='$lastname' ,username='$username',password='$password'WHERE id='$id'") or die(mysql_error());

                // once saved, redirect back to the view page
                header("Location: dashboard.php");
            }
        } else {
            // if the 'id' isn't valid, display an error
            echo 'Error!';
        }
    } else {
// if the form hasn't been submitted, get the data from the db and display the form
        // get the 'id' value from the URL (if it exists), making sure that it is valid (checing that it is numeric/larger than 0)
        if (isset($_GET['id']) && is_numeric($_GET['id']) && $_GET['id'] > 0) {
            // query db
            $id = $_GET['id'];
            $result = mysql_query("SELECT * FROM users WHERE id=$id") or die(mysql_error());
            $row = mysql_fetch_array($result);

            // check that the 'id' matches up with a row in the databse
            if ($row) {

                // get data from db
                $firstname = $row['firstname'];
                $lastname = $row['lastname'];
                $username = $row['username'];
                $password = $row['password'];

                // show form
                renderForm($id, $firstname, $lastname, $username, $password, '');
            } else {
                // if no match, display result
                echo "No results!";
            }
        } else {
            // if the 'id' in the URL isn't valid, or if there is no 'id' value, display an error
            echo 'Error!';
        }
    }
    ?>
