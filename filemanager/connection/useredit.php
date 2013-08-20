<?php
include 'connect.php';
?>
<html>
    <head>
        <meta name="description" content="Php Code for View, Search, Edit and Delete Record" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Update  user  Record</title>
    </head>
    <body>
        <?php
        if ($_POST == "update") {
            $id = $_POST["id"];
            $firstname = $_POST["firstname"];
            $lastname = $_POST["lastname"];
            $username = $_POST["username"];
            $password = $_POST["password"];

            $query = "update users set firstname='$firstname',lastname='$lastname',username='$username','password='$password' where id=$id";
            mysql_query($query);

            echo "<center>Successfully Updated in DATABASE</center>";
            include("search.php");
        }
        ?>
    <center>
        <h1><u>Student Database</u></h1>
    </center>

    <?
    $id = $_POST["id"];
    $query = "select * from users where id='$id'";
    $result = mysql_query($query);
    while ($row = mysql_fetch_array($result)) {
        <form name = "update" method = "post" action = "useredit.php">

        <table style = " border:1px solid silver" cellpadding = "5px" cellspacing = "0px" align = "center" border = "0">
        <tr>
        <td colspan = "4" style = "background:#0066FF; color:#FFFFFF; font-size:20px">Edit user information</td>
        </tr>
        <tr>

        echo "<tr ><td>", $row[0], "</td><td><a href='view.php?roll=", $row[0], "'>", $row[1], " ", "</a></td><td><a href='edit.php?roll=", $row[1], "'>Edit</a> | <a href='del.php?roll=", $row[1], "'>Delete</a></td></tr>";
        <tr>

        <td>Enter Firstname</td>
        <td><input type = "text" name = "firstname" size = "20" value = "<? echo $row[2]; ?>"></td>
        </tr>
        <tr>


        <td>Enter lastname</td>
        <td><input type = "text" name = "lastname" size = "20" value = "<? echo $row[3]; ?>"></td>
        </tr>
        <tr>
        <td>Enter username</td>
        <td><input type = "text" name = "username" size = "20" value = "<? echo $row[4] ?>"></td>
        </tr>
        <tr>


        <td>Enter Password</td>
        <td><input type = "text" name = "password" size = "20" value = "<? echo $row[5]; ?>"></td>
        </tr>


        <tr>
        <td colspan = "4" align = "center"><input type = "hidden" name = "do" value = "update">
        <input type = "submit" value = "UPDATE RECORD"></td>
        </tr>
        ?>
    </table>
    </form>

<? } ?>
<p align="center"><a href="index.php">Go Back to Home</a></p>
</body>
</html>

