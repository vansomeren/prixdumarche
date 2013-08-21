<?php

/*
 * Dashboard Controller
 */

class Dashboard extends Admin_Controller {

    public function _construct() {
        parent::__construct();
        $this->load->library('session');
    }

    public function index() {

        $this->data['products'] = $this->products_model->get();

        $this->data['catogries'] = $this->category_model->get();
        //load view

        $this->data['subview'] = 'admin/dashboard/index';
        $this->load->view('admin/components/_layout', $this->data);
    }

    public function modal() {
        $this->load->view('admin/_layout_modal', $this->data);
    }

}

?>
