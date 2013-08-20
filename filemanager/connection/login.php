<?php
// Connects to your Database
mysql_connect("localhost", "root", "someren") or die(mysql_error());
mysql_select_db("files") or die(mysql_error());
//Checks if there is a login cookie
if (isset($_COOKIE['ID_my_site'])) {
//if there is, it logs you in and directes you to the members page
    $username = $_COOKIE['ID_my_site'];
    $pass = $_COOKIE['Key_my_site'];
    $check = mysql_query("SELECT * FROM users WHERE username = '$username'") or die(mysql_error());
    while ($info = mysql_fetch_array($check)) {
        if ($pass != $info['password']) {

        } else {
            header("Location: main.php");
        }
    }
}
//if the login form is submitted
if (isset($_POST['submit'])) { // if form has been submitted
    // makes sure they filled it in
    if (!$_POST['username'] | !$_POST['password']) {
        die('You did not fill in a required field.');
    }
    // checks it against the database
    if (!get_magic_quotes_gpc()) {

    }
    $check = mysql_query("SELECT * FROM users WHERE username = '" . $_POST['username'] . "'") or die(mysql_error());
    //Gives error if user dosen't exist
    $check2 = mysql_num_rows($check);
    if ($check2 == 0) {
        die('That user does not exist in our database. <a href=signup.php>Click Here to Register</a>');
    }
    while ($info = mysql_fetch_array($check)) {
        $_POST['password'] = stripslashes($_POST['password']);
        $info['password'] = stripslashes($info['password']);
        $_POST['password'] = md5($_POST['password']);
        //gives error if the password is wrong
        if ($_POST['password'] != $info['password']) {
            die('Incorrect password, please try again. <a href=index.php>Click Here to Log In</a>');
        } else {
            // if login is ok then we add a cookie
            $_POST['username'] = stripslashes($_POST['username']);
            $hour = time() + 3600;
            setcookie(ID_my_site, $_POST['username'], $hour);
            setcookie(Key_my_site, $_POST['password'], $hour);
            //then redirect them to the members area
            header("Location: main.php");
        }
    }
} else {
    // if they are not logged in
    ?>
    <form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post">
        <table border='1' cellpadding='5' cellspacing='0' bordercolor='#FF9900' bgcolor="#CCFFFF">
            <tr><td colspan=2><h1>Login</h1></td></tr>
            <tr><td>Username:</td><td>
                    <input type="text" name="username" maxlength="40">
                </td></tr>
            <tr><td>Password:</td><td>
                    <input type="password" name="password" maxlength="50">
                </td></tr>
            <tr><td colspan="2" align="right">
                    <input type="submit" name="submit" value="Login">
                </td></tr>
        </table>
        <br />
        <a href="/hrd/orig/register.php">Register Here</a>
    </form>
    <?php
}
?>