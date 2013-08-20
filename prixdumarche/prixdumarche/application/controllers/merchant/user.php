<?php

/*
 * title:Merchant(User) Controller
 * Description: Controller for merchant activities
 */

class User extends Merchant_Controller {

    function __construct() {
        parent::__construct();
        $this->load->model('merchants_model');
    }

//list merchant
    function index($id = NULL) {
        $id = $this->session->userdata('id');

        $this->data['merchant'] = $this->merchants_model->get_by(array('id' => $id));
//load view
        $this->data['subview'] = 'merchants/users/index';
        $this->load->view('merchants/_layout', $this->data);
    }

//show registration form
    public function signup() {


        $rule = $this->merchants_model->_rules;
        $this->form_validation->set_rules($rule);
        if ($this->form_validation->run() === TRUE) {
            $data = $this->merchants_model->array_from_post(array('name', 'email', 'phone', 'password'));
            $data['password'] = $this->merchants_model->hash($data['password']);
            $this->merchants_model->save($data);
            $this->session->set_userdata($data);
            redirect('signin');
        } else {
            $this->session->set_flashdata('error', '<div class="alert alert-error">Something went wrong.<br>
                  Please try again</div>');
        }
//load the view

        $this->data['subview'] = 'merchants/register'; //registration form

        $this->load->view('main_layout', $this->data);
    }

//login
    public function login() {
        $rules = $this->merchants_model->login_rules;
        (($this->merchants_model->loggedin() == FALSE ) || redirect('merchant/dashboard'));
        $this->form_validation->set_rules($rules);
        if ($this->form_validation->run() == TRUE) {
            if ($this->merchants_model->login() == TRUE) {

                redirect('merchant/dashboard/index');
            } else {
                $this->session->set_flashdata('error', '<div class="alert alert-error">Invalid Login Credentials</div>');
                redirect('signin');
            }
        }
//load view

        $this->data['subview'] = 'merchants/users/login';
        $this->load->view('main_layout', $this->data);
    }

//logout
    public function logout() {
        $this->merchants_model->logout();
        redirect('signin');
    }

//edit user (update)
    public function edit($id = NULL) {
        $id = ($this->session->userdata('id'));
        if ($id) {
            $this->data['fields'] = $this->merchants_model->get_by(array('id' => $id));
            $rules = $this->merchants_model->_rules;
            $id || $rules['password'] = '|required';
            $this->form_validation->set_rules($rules);
            if ($this->form_validation->run() == TRUE) {
                $data = $this->merchants_model->array_from_post(array('name', 'email', 'password', 'phone'));
                $data['password'] = $this->merchants_model->hash($data['password']);
                $this->merchants_model->save($data, $id);
                dump($data);
//redirect('merchant/user'); // list user
            } else {
                $this->session->set_flashdata('error', '<div class="alert alert-error"> Unsuccessful !</div>');
            }
        }
        $this->data['subview'] = 'merchants/users/edit';
        $this->load->view('merchants/_layout', $this->data);
    }

//unique email
    public function _unique_email($str) {
        $id = $this->uri->segment(4);
        $this->db->where('email', $this->input->post('email'));
        !$id || $this->db->where('id !=', $id);
        $merchants = $this->merchants_model->get();
        if (count($merchants)) {
            $this->form_validation->set_message('<div class="alert alert-error">_unique_email', '%s should be unique</div>');
            return FALSE;
        }
        return TRUE;
    }

}

?>
