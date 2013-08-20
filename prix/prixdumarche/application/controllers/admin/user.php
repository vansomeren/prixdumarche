<?php

class User extends Admin_Controller {

    function __construct() {
        parent::__construct();
    }

//show all users
    public function index() {
        $this->data['users'] = $this->user_model->get();
        //load view
        $this->data['subview'] = 'admin/user/index';
        $this->load->view('admin/components/_layout', $this->data);
    }

//edit users (update)
    public function edit($id = NULL) {
        if ($id) {
            $this->data['user'] = $this->user_model->get($id);
            count($this->data['user']) || $this->data['error'][] = '<div class="alert alert-error">Invalid Request </div>'; //user not found
        } else {
            $this->data['user'] = $this->user_model->get_new_user();
        }

        $rules = $this->user_model->_rule;
        $id || $rules['password'] = '|required';
        $this->form_validation->set_rules($rules);
        if ($this->form_validation->run() == TRUE) {
            $data = $this->user_model->array_from_post(array('name', 'email', 'password'));
            $data['password'] = $this->user_model->hash($data['password']);
            $this->user_model->save($data, $id);
            redirect('admin/user'); // list users
        }
        $this->data['subview'] = 'admin/user/edit';
        $this->load->view('admin/components/_layout', $this->data);
    }

//delete users
    public function delete($id) {
        $this->user_model->delete($id);
        redirect('admin/user'); //admin/user || list users
    }

//login
    public function login() { {

            $rules = $this->user_model->_rules;
            $this->user_model->loggedin() == FALSE || redirect('admin/dashboard');

            $this->form_validation->set_rules($rules);
            if ($this->form_validation->run() == TRUE) {
                if ($this->user_model->do_login() == TRUE) {
                    $this->session->set_flashdata('success', '<div class="alert alert-success">Welcome</div>');
                    redirect('admin/dashboard');
                } else {
                    $this->session->set_flashdata('error', '<div class="alert alert-error">Invalid Credentials</div>');
                    redirect('admin');
                }
            }
            //load view
            $this->data['subview'] = 'admin/user/login';
            $this->load->view('admin/_layout_main', $this->data);
        }
    }

//logout
    public function logout() {
        $this->user_model->do_logout();
        redirect('admin'); //admin/user/login
    }

//unique email
    public function _unique_email($str) {
        $id = $this->uri->segment(4);
        $this->db->where('email', $this->input->post('email'));
        !$id || $this->db->where('id !=', $id);
        $user = $this->user_model->get();
        if (count($user)) {
            $this->form_validation->set_message('<div class="alert alert-error">_unique_email', '%s should be unique</div>');
            return FALSE;
        }
        return TRUE;
    }

}

?>
