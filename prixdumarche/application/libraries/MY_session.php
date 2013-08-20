<?php

class MY_Session extends CI_Session {

//listen to HTTP_X_REQUESTED_WITH
    function sess_update() {
        if (isset($_SERVER['HTTP_X_REQUESTED_WITH']) && $_SERVER['HTTP_X_REQUESTED_WITH'] !== 'XMLHttpRequest') {
            parent::sess_update();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
