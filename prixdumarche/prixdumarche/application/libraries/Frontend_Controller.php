<?php

class Frontend_Controller extends MY_Controller {

    function __construct() {
        parent::__construct();
        $this->load->helper('url');

        $this->data['meta_title'] = $this->config->item('site_name');
    }

}

