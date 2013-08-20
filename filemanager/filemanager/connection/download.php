
<?php
//database connection
$con = mysql_connect('localhost', 'root', 'someren') or die(mysql_error());
//select database
$db = mysql_select_db('files', $con);
$query = "SELECT id, name FROM file";
$result = mysql_query($query) or die('Error, query failed');
if (mysql_num_rows($result) == 0) {
    echo "Database is empty <br>";
} else {
    while (list($id, $name) = mysql_fetch_array($result)) {
        ?>
        <a href="download.php?id=<?php echo urlencode($id); ?>"
           ><?php echo urlencode($name); ?></a> <br>
           <?php
       }
   }
   mysql_close();
   ?>

<?php
if (isset($_GET['id'])) {
// if id is set then get the file with the id from database
    $con = mysql_connect('localhost', 'root', 'someren') or die(mysql_error());
    $db = mysql_select_db('', $con);
    $id = $_GET['id'];
    $query = "SELECT name, type, size, content " .
            "FROM file WHERE id = '$id'";
    $result = mysql_query($query) or die('Error, query failed');
    list($name, $type, $size, $content) = mysql_fetch_array($result);
    header("Content-length: $size");
    header("Content-type: $type");
    header("Content-Disposition: attachment; filename=$name");
    ob_clean();
    flush();
    echo $content;
    mysql_close();
    exit;
}
?>
