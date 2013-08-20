<?php

class Products_Controller extends MY_Controller {

    function __construct() {
        parent::__construct();
        $this->data['meta_title'] = config_item('site_name');
        $this->load->library('form_validation');
        $this->load->helper('url', 'html');
        $this->load->model('products_model');
    }

}

?>
