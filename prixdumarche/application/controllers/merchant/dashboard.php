<?php

class Dashboard extends Merchant_Controller {

    function __construct() {
        parent::__construct();
    }

    function index($id = NULL) {
        $id = $this->session->userdata('id');
        if ($id) {
            $this->data['product'] = $this->products_model->get_by(array('merchant_id' => $id));
            count($this->data['product']) || $this->data['error'][] = '<div class="alert alert-error">No Products Found </div>'; //user not found
        }
        //load view
        $this->data['subview'] = 'merchants/dashboard/index';
        $this->load->view('merchants/_layout', $this->data);
    }

}

?>
