<?php

class Merchants_Model extends MY_Model {

    protected $_table_name = 'merchants';
    protected $_primary_key = 'id';
    protected $_order_by = 'email';
    protected $_timestamps = TRUE;
//    public function get_new() {
//        $merchant = new stdClass();
//        $merchant->name = '';
//        $merchant->email = '';
//        $merchant->password = '';
//        $merchant->phone = '';
//        $merchant->created = '';
//        $merchant->modified = '';
//        return $merchant;
//    }

    public $_rules = array(
        'name' => array(
            'field' => 'name',
            'label' => 'Full Name',
            'rules' => 'trim|required|xss_clean'
        ),
        'email' => array(
            'field' => 'email',
            'label' => 'Email Address',
            'rules' => 'trim|required|valid_email|callback__unique_email|xss_clean'
        ),
        'phone' => array(
            'field' => 'phone',
            'label' => 'Phone Number',
            'rules' => 'trim|required|xss_clean'
        ),
        'password' => array(
            'field' => 'password',
            'label' => 'Password',
            'rules' => 'trim|required|matches[confirm_password]|xss_clean'
        ),
        'confirm_password' => array(
            'field' => 'confirm_password',
            'label' => 'Password Confirm',
            'rules' => 'trim|required|matches[password]|xss_clean'
        ),
    );
    public $login_rules = array(
        'email' => array(
            'field' => 'email',
            'label' => 'Email',
            'rules' => 'trim|required|valid_email|xss_clean'
        ),
        'password' => array(
            'field' => 'password',
            'label' => 'Password',
            'rules' => 'trim|required|xss_clean'
        ),
    );

    public function login() {
        $merchant = $this->get_by(array(
            'email' => $this->input->post('email'),
            'password' => $this->hash($this->input->post('password'))
                ), TRUE);
        if (count($merchant)) {
            //log in merchant
            $data = array(
                'id' => $merchant->id,
                'name' => $merchant->name,
                'email' => $merchant->email,
                'loggedin' => TRUE
            );
            // var_dump($data);
            $this->session->set_userdata($data);
        }
    }

    public function logout() {

        $this->session->sess_destroy();
    }

    public function loggedin() {
        return (bool) $this->session->userdata('loggedin');
    }

    public function hash($string) {
        return hash('sha512', $string . config_item('encryption_key'));
    }

}

?>
