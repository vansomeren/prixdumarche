<?php

$mysql_host = 'localhost';
$mysql_user = 'root';
$mysql_pass = 'someren';
$mysql_db = 'files';
$conn_error = 'could not connect';
$success = 'connected';
if (!mysql_connect($mysql_host, $mysql_user, $mysql_pass) || !mysql_select_db($mysql_db)) {
    die(mysql_errno());
}
?>
