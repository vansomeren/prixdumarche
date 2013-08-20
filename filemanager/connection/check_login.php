<?php

include 'connect.php';
//username and password sent from form
$username = $_POST['username'];
$password = $_POST['password'];
//to protect mysql injections
$username = stripcslashes($username);
$password = stripcslashes($password);
$username = mysql_real_escape_string($username);
$password = mysql_real_escape_string($password);
$sql = "SELECT * FROM $tbl_name WHERE username='$username'and password='$password'";
$result = mysql_query($sql);

$count = mysql_num_rows($result);
if ($count['status'] === '1') { // check the value of the 'status' in the db
    //go to admin area
    header("Location: dashboard.php");
} else {
    //go to members area
    header("Location: file.php");
}
?>
