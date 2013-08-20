<?

if (isset($_GET['fid'])) {
// connect to the database
    include "connect.php";

// query the server for the picture
    $fid = $_GET['fid'];
    $query = "SELECT * FROM files WHERE fid = '$fid'";
    $result = mysql_query($query) or die(mysql_error());

// define results into variables
    $name = mysql_result($result, 0, "name");
    $size = mysql_result($result, 0, "size");
    $type = mysql_result($result, 0, "type");
    $content = mysql_result($result, 0, "content");

// give our picture the proper headers...otherwise our page will be confused
    header("Content-Disposition: attachment; filename=$name");
    header("Content-length: $size");
    header("Content-type: $type");
    echo $content;

    mysql_close();
} else {
    die("No file ID given...");
}
?>