<?php

class Merchant extends Merchant_Controller {

    function __construct() {
        parent::__construct();
        $this->load->library('session');
        $this->load->helper('url');
    }

//show all merchants
    public function index() {
        $this->data['merchants'] = $this->merchants_model->get();
        //load view
        $this->data['subview'] = 'admin/merchant/index';
        $this->load->view('admin/components/_layout', $this->data);
    }

//edit merchants (update)
    public function edit($id = NULL) {
        if ($id) {
            $this->data['merchant'] = $this->merchants_model->get($id);
            count($this->data['merchant']) || $this->data['error'][] = '<div class="alert alert-error">Invalid Request </div>'; //merchant not found
        } else {
            $this->data['merchant'] = $this->merchants_model->get_new_merchant();
        }

        $rules = $this->merchants_model->rule;
        $id || $rules['password'] = '|required';
        $this->form_validation->set_rules($rules);
        if ($this->form_validation->run() == TRUE) {
            $data = $this->merchants_model->array_from_post(array('name', 'email', 'password'));
            $data['password'] = $this->merchants_model->hash($data['password']);
            $this->merchants_model->save($data, $id);
            redirect('admin/merchant'); // list merchants
        }
        $this->data['subview'] = 'admin/merchant/edit';
        $this->load->view('admin/components/_layout', $this->data);
    }

//delete merchants
    public function delete($id) {
        $this->merchants_model->delete($id);
        redirect('admin/merchant'); //admin/merchant || list merchants
    }
    public function view($id = NULL) {
        //merchant profile
        if ($id) {
            $this->data['merchant'] = $this->merchants_model->get_by(array('id' => $id));
        } else {
            $this->data['error'] = '<div class="alert alert-error">Invalid Request </div>';
        }
        //merchant product count
        $this->data['product'] = $this->products_model->get_by(array('merchant_id' => $id));
        count($this->data['product']) || $this->data['error'][] = '<div class="alert alert-error">No Products Found </div>'; //error
        //load view
        $this->data['subview'] = 'admin/merchant/view';
        $this->load->view('admin/components/_layout', $this->data);
    }

//unique email
    public function _unique_email($str) {
        $id = $this->uri->segment(4);
        $this->db->where('email', $this->input->post('email'));
        !$id || $this->db->where('id !=', $id);
        $merchant = $this->merchants_model->get();
        if (count($merchant)) {
            $this->form_validation->set_message('<div class="alert alert-error">_unique_email', '%s should be unique</div>');
            return FALSE;
        }
        return TRUE;
    }

}

?>
