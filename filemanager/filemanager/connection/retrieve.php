
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>File Manager</title>
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
                                <li class="active"><a href="retrieve.php">Files</a></li>
                                <li class="active"><a href="upload.php">Upload File</a></li>

                            </ul>
                        </div>
                    </div>
                </div><!-- /.navbar -->
            </div>
            <div class="jumbotron">
                <form name="search" method="post" class="modal-form">

                    <table class="table">
                        <tr>

                            <td><input type="text" name="search" size="1000" placeholder="search" class="input-block-level"></td>
                            <td><input type="submit" value="Search" class="btn-block" /></td>
                        </tr>




                        <?php
                        Print "<th>Firstname:</th> <th>Lastname:</th><th>username</th><th>Password</th>";
                        include 'connect.php';
                        //search query
                        $search = $_POST["search"];
                        $flag = 0;
                        //$query = "select * from users where  firstname like '%$search%' ";
                        //$result = mysql_query($query);
                        $per_page = 5;

                        // figure out the total pages in the database
                        $result = mysql_query("SELECT id,name,size,type FROM file where name like '%$search%'");
                        $total_results = mysql_num_rows($result);
                        $total_pages = ceil($total_results / $per_page);

                        // check if the 'page' variable is set in the URL (ex: view-paginated.php?page=1)
                        if (isset($_GET['page']) && is_numeric($_GET['page'])) {
                            $show_page = $_GET['page'];

                            // make sure the $show_page value is valid
                            if ($show_page > 0 && $show_page <= $total_pages) {
                                $start = ($show_page - 1) * $per_page;
                                $end = $start + $per_page;
                            } else {
                                // error - show first set of results
                                $start = 0;
                                $end = $per_page;
                            }
                        } else {
                            // if page isn't set, show first set of results
                            $start = 0;
                            $end = $per_page;
                        }

                        // display pagination

                        echo "<p><a href='view.php'>View All</a> | <b>View Page:</b> ";
                        for ($i = 1; $i <= $total_pages; $i++) {
                            echo "<a href='retrieve.php?page=$i'>$i</a> ";
                        }
                        echo "</p>";

                        // display data in table
                        // echo "<table border='1' cellpadding='10'>";
                        //echo "<tr> <th>ID</th> <th>First Name</th> <th>Last Name</th> <th>Username</th> <th>Password</th></tr>";
                        // loop through results of database query, displaying them in the table
                        for ($i = $start; $i < $end; $i++) {
                            // make sure that PHP doesn't try to show results that don't exist
                            if ($i == $total_results) {
                                break;
                            }

                            // echo out the contents of each row into a table
                            echo "<tr>";
                            //echo '<td>' . mysql_result($result, $i, 'id') . '</td>';
                            echo '<td>' . mysql_result($result, $i, 'name') . '</td>';
                            echo '<td>' . mysql_result($result, $i, 'size') . '</td>';
                            echo '<td>' . mysql_result($result, $i, 'type') . '</td>';
                            echo '<td>' . mysql_result($result, $i, 'content') . '</td>';
                            echo '<td><a href="download.php?id=' . mysql_result($result, $i, 'id') . '">Download</a></td>';
                            echo '<td><a href="delete_file.php?id=' . mysql_result($result, $i, 'id') . '">Delete</a></td>';
                            echo "</tr>";
                        }
                        // close table>
                        echo "</table>";
                        /*
                          include 'connect.php';
                          //search query
                          $search = $_POST["search"];
                          $flag = 0;
                          //$qry = "select * from file";
                          $query = "SELECT id,name,size,type FROM file  where  name like '%$search%' ";
                          $result = mysql_query($query);
                          while ($row = mysql_fetch_array($result)) {
                          $flag = 1;
                          echo "<tr ><td>", $row[1], "</td><td>", $row[2], "</td><td>", $row[3], "</td><td>", $row[4], "</td><td>", $row[5], "</a></td><td><a href='edit.php?roll=", $row[1], "'>Edit</a> | <a href='del.php?roll=", $row[1], "'>Delele</a>| <a href='download.php?roll=", $row[1], "'>Download</a></td></tr>";
                          }
                          if ($flag == 0)
                          echo "<tr><td colspan='3' align='center' style='color:red'>Record not found</td></tr>"; */
                        ?>

                        <!--*/-->



                    </table>

                </form>
            </div>