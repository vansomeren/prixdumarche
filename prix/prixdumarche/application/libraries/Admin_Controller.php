<?php

class Admin_Controller extends MY_Controller {

    function __construct() {
        parent::__construct();

        $this->load->helper('form');
        $this->load->library('form_validation');
        $this->load->library('session');
        $this->load->model('user_model');
        $this->data['meta_title'] = config_item('site_name');
        $this->data['title'] = 'Control Panel';
        $this->data['p'] = 'Login using your credentials';



        /*
         *  Login check
         * $route['admin'] = 'admin/user/login';
          $route['signout'] = 'admin/user/logut';
         */
        $exception_uris = array(
            'admin',
            'signout'
        );
        if (in_array(uri_string(), $exception_uris) == FALSE) {
            if ($this->user_model->loggedin() == FALSE) {
                redirect('admin');
            }
        }
    }

}

?>
