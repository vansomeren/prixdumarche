<?php

/*
 * title: Merchant Controller
 * Descritpion: This class handle Merchants Logic
 */

class Merchant_Controller extends MY_Controller {

    function __construct() {
        parent::__construct();

        $this->load->model('merchants_model');
        $this->data['meta_title'] = $this->config->item('site_name');
    }

}

