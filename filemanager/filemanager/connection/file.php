<?php

if (!isset($_POST['name'])) {
    $name = $_POST['name'];
    if (!empty($name)) {



        $handle = fopen('names.txt', 'w');
        fwrite($handle, $name, "\n");
        //fwrite($handle, 'Billy');
        fclose($handle);
        echo 'Curent names in the file';
        $readdir = file('name.txt');
        foreach ($readdir as $fname) {
            echo $fname . ',';
        }
    } else {
        echo 'Please type a name';
    }
}
?>