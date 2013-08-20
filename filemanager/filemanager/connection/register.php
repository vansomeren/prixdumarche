<?php

include 'connect.php';
$sql = "SELECT COUNT(*) FROM users WHERE username = '{$_POST['uname']}'";
$sql_result = mysql_query($sql);
if (mysql_result($sql_result, 0) > 0) {
    //Echo "This username is already in use.Please choose another.";
    echo '<script type="text/javascript">alert("This username is already taken. Please choose another one"); </script>)';
} else {
    $query = "INSERT INTO users(firstname,lastname,username,password)VALUES('$_POST[fname]','$_POST[lname]','$_POST[uname]','$_POST[pword]')";
}

if (!mysql_query($query)) {
    die('Error: ' . mysql_error());
} else {
    echo '<script type="text/javascript">alert("Success"); </script>';
}
header("location:index.php");
?>