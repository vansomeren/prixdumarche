<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Template &middot; Bootstrap</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le styles -->
        <link href="../css/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 20px;
                padding-bottom: 60px;
            }

            /* Custom container */
            .container {
                margin: 0 auto;
                max-width: 1000px;
            }
            .container > hr {
                margin: 60px 0;
            }

            /* Main marketing message and sign up button */
            .jumbotron {
                margin: 80px 0;
                text-align: center;
            }
            .jumbotron h1 {
                font-size: 100px;
                line-height: 1;
            }
            .jumbotron .lead {
                font-size: 24px;
                line-height: 1.25;
            }
            .jumbotron .btn {
                font-size: 21px;
                padding: 14px 24px;
            }

            /* Supporting marketing content */
            .marketing {
                margin: 60px 0;
            }
            .marketing p + h4 {
                margin-top: 28px;
            }


            /* Customize the navbar links to be fill the entire space of the .navbar */
            .navbar .navbar-inner {
                padding: 0;
            }
            .navbar .nav {
                margin: 0;
                display: table;
                width: 100%;
            }
            .navbar .nav li {
                display: table-cell;
                width: 1%;
                float: none;
            }
            .navbar .nav li a {
                font-weight: bold;
                text-align: center;
                border-left: 1px solid rgba(255,255,255,.75);
                border-right: 1px solid rgba(0,0,0,.1);
            }
            .navbar .nav li:first-child a {
                border-left: 0;
                border-radius: 3px 0 0 3px;
            }
            .navbar .nav li:last-child a {
                border-right: 0;
                border-radius: 0 3px 3px 0;
            }
        </style>
        <link href="../css/bootstrap-responsive.css" rel="stylesheet">

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

        <div class="container">

            <div class="masthead">
                <h3 class="muted">Admin Dashboard</h3>
                <div class="navbar">
                    <div class="navbar-inner">
                        <div class="container">
                            <ul class="nav">
                                <li class="active"><a href="dashboard.php">Users</a></li>
                                <li><a href="retrieve.php">Files</a></li>
                                <li><a href="upload.php">Upload file</a></li>

                            </ul>
                        </div>
                    </div>
                </div><!-- /.navbar -->
            </div>
            <div class="jumbotron">
                <body>
                    <form method="post" enctype="multipart/form-data" class="form-signin" >

                        <h2 class="form-signin-heading">Please upload a file</h2>

                        <input type="hidden" name="MAX_FILE_SIZE" value="16000000" class="btn btn-large btn-primary">
                        <input name="userfile" type="file" id="userfile" class="btn-block">
                        <input name="upload" type="submit" class="btn btn-large btn-primary" id="upload" value=" Upload "></td>

                    </form>
                </body>
                </html>
                <?php
                if (isset($_POST['upload']) && $_FILES['userfile']['size'] > 0) {
                    $fileName = $_FILES['userfile']['name'];
                    $tmpName = $_FILES['userfile']['tmp_name'];
                    $fileSize = $_FILES['userfile']['size'];
                    $fileType = $_FILES['userfile']['type'];
                    $fileType = (get_magic_quotes_gpc() == 0 ? mysql_real_escape_string(
                                            $_FILES['userfile']['type']) : mysql_real_escape_string(
                                            stripslashes($_FILES['userfile'])));
                    $fp = fopen($tmpName, 'r');
                    $content = fread($fp, filesize($tmpName));
                    $content = addslashes($content);
                    fclose($fp);
                    if (!get_magic_quotes_gpc()) {
                        $fileName = addslashes($fileName);
                    }
                    $con = mysql_connect('localhost', 'root', 'someren') or die(mysql_error());
                    $db = mysql_select_db('files', $con);
                    if ($db) {
                        $query = "INSERT INTO file(name, size, type, content ) " .
                                "VALUES ('$fileName', '$fileSize', '$fileType', '$content')";
                        mysql_query($query) or die('Error, query failed');
                        mysql_close();
                        echo "<br>File $fileName uploaded<br>";
                    } else {
                        echo "file upload failed";
                    }
                }
                ?>

