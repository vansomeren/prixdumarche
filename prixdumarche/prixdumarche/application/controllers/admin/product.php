<?php

class Product extends Products_Controller {

    function __construct() {
        parent::__construct();
        $this->load->model('products_model');
        $this->load->helper('form');
    }

//show all products
    public function index() {


        $this->data['products'] = $this->products_model->get();

        $this->data['subview'] = 'admin/product/index';
        $this->load->view('admin/components/_layout', $this->data);
    }

//edit products (update)
    public function edit($id = NULL) {
        $id = $this->session->userdata('id');
        if ($id) {
            $this->data['product'] = $this->products_model->get_by(array('merchant_id' => $id));
            count($this->data['product']) || $this->data['error'][] = '<div class="alert alert-error">Product Not Found </div>'; //product not found
        } else {
            $this->data['product'] = $this->products_model->get_new_product();
        }
//setup form
        $rules = $this->products_model->_rules;
        $this->form_validation->set_rules($rules);
        //process form
        if ($this->form_validation->run() == TRUE) {
            $data = $this->products_model->array_from_post(
                    array(
                        'title',
                        'slug',
                        'body',
                        'pubdate')
            );
            $this->products_model->save($data, $id);
            redirect('admin/product'); //admin/product/index
        }
        //load view
        $this->data['subview'] = 'Heello'; //admin/product/edit
        $this->load->view('admin/components/_layout', $this->data);
    }

//delete products
    public function delete($id) {
        $this->products_model->delete($id);
        redirect('admin/product'); //admin/product/index
    }

}

?>
