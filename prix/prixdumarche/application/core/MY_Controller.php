<?php

class MY_Controller extends CI_Controller {

    public $data = array();

    function __construct() {

        parent::__construct();
        $this->data['errors'] = array();

        $this->data['site_name'] = config_item('site_name');
        $this->load->dbforge();
        $this->load->dbutil();
        $this->load->helper(array('form', 'url', 'html'));
        $this->load->library(array('form_validation', 'session'));
        $this->form_validation->set_error_delimiters('<div class="alert alert-error">', '</div>');
    }

}

