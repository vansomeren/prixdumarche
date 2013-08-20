<?php

function add_meta_title($string) {
    $CI = & get_instance();
    $CI->data['meta_title'] = e($string) . ' - ' . $CI->data['meta_title'];
}

function bt_edit($uri) {
    return anchor($uri, '<i class="icon-edit"> </i>');
}

function bt_delete($uri) {
    return anchor($uri, '<i class="icon-remove"> </i>', array('onclick' => "return confirm('You are about to delete this user account. This cannot be undone. Are you sure?')"));
}

function bt_view($uri) {
    return anchor($uri, '<i class="icon-eye-open"> </i>');
}

function product_link($product) {
    return 'product/' . intval($product->id) . '/' . e($product->name);
}

function product_links($products) {
    $string = '<ul>';
    foreach ($products as $product) {
        $url = product_link($product);
        $string .= '<li>';
        $string .= '<h3>' . anchor($url, ($product->name . '')) . '</h3> ';
        $string .= '<p class="pubdate">' . e($product->pubdate) . '</p>';
        $string .= '</li>';
    }
    $string .= '</ul>';
    return $string;
}

function get_excerpt($product, $numwords = 20) {
    $string = '';
    $url = product_link($product);
    $string .= '<h2>' . anchor($url, e($product->name)) . '</h2>';
    $string .= '<p class="pubdate">' . e($product->pubdate) . '</p>';
    $string .= '<p>' . e(limit_to_numwords(strip_tags($product->description), $numwords)) . '</p>';
    $string .= '<p>' . anchor($url, 'Read more &gt;', array('title' => e($product->name))) . '</p>';
    return $string;
}

function limit_to_numwords($string, $numwords) {
    $excerpt = explode(' ', $string, $numwords + 1);
    if (count($excerpt) >= $numwords) {
        array_pop($excerpt);
    }
    $excerpt = implode(' ', $excerpt);
    return $excerpt;
}

function e($string) {
    return htmlentities($string);
}

/**
 * Dump helper. Functions to dump variables to the screen, in a nicley formatted manner.
 * @author Joost van Veen
 * @version 1.0
 */
if (!function_exists('dump')) {

    function dump($var, $label = 'Dump', $echo = TRUE) {
        // Store dump in variable
        ob_start();
        var_dump($var);
        $output = ob_get_clean();

        // Add formatting
        $output = preg_replace("/\]\=\>\n(\s+)/m", "] => ", $output);
        $output = '<pre style="background: #FFFEEF; color: #000; border: 1px dotted #000; padding: 10px; margin: 10px 0; text-align: left;">' . $label . ' => ' . $output . '</pre>';

        // Output
        if ($echo == TRUE) {
            echo $output;
        } else {
            return $output;
        }
    }

}


if (!function_exists('dump_exit')) {

    function dump_exit($var, $label = 'Dump', $echo = TRUE) {
        dump($var, $label, $echo);
        exit;
    }

}
?>

