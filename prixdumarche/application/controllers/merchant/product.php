<?php

class Product extends Products_Controller {

    function __construct() {
        parent::__construct();
        $this->data['recent_items'] = $this->products_model->get_recent();
    }

//show all products with current user(logged in user)
    public function index($id = NULL) {



        $id = $this->session->userdata('id');
        if ($id) {

            $this->data['product'] = $this->products_model->get_by(array('merchant_id' => $id));
            count($this->data['product']) || $this->data['error'][] = '<div class="alert alert-error">No Products Found </div>';
        } else {
            $this->data['product'] = $this->products_model->get_new();
        }

        //load view

        $this->data['subview'] = 'merchants/products/index';
        $this->load->view('merchants/_layout', $this->data);
    }

//edit products (update)
    public function edit($id = NULL) {

        $this->data['category'] = $this->category_model->get();
        if ($id) {
            $this->data['product'] = $this->products_model->get($id);
            count($this->data['product']) || $this->data['error'][] = '<div class="alert alert-error">Product Not Found </div>'; //product not found
        } else {
            $this->data['product'] = $this->products_model->get_new();
        }
//setup form
        $rules = $this->products_model->rules;
        $this->form_validation->set_rules($rules);
        //process form

        if ($this->form_validation->run() == TRUE) {

            $data = $this->products_model->array_from_post(
                    array(
                        'name',
                        'category',
                        'description',
                        'price',
                        'pubdate',
                        'merchant_id',
                        'category_id'
                    )
            );
            $this->products_model->save($data, $id);
            redirect('merchant/product'); //list
        }
        //load view
        $this->data['subview'] = 'merchants/products/edit';
        $this->load->view('merchants/_layout', $this->data);
    }

//delete products
    public function delete($id) {
        $this->products_model->delete($id);
        redirect('merchant/product/'); //merchant/product/index
    }

}

?>
