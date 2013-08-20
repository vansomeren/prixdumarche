<?php

class User_Model extends MY_Model {

    protected $_table_name = 'users';
    protected $_primary_key = 'id';
    protected $_order_by = 'name';
    protected $_timestamps = True;
    public $_rules = array(
        'email' => array(
            'field' => 'email',
            'label' => 'Email',
            'rules' => 'trim|required|valid_email|xss_clean'
        ),
        'password' => array(
            'field' => 'password',
            'label' => 'Password',
            'rules' => 'trim|required'
        ),
    );
    public $_rule = array(
        'name' => array(
            'field' => 'name',
            'label' => 'Name',
            'rules' => 'trim|required|xss_clean'
        ),
        'email' => array(
            'field' => 'email',
            'label' => 'Email',
            'rules' => 'trim|required|valid_email|callback__unique_email|xss_clean'
        ),
        'password' => array(
            'field' => 'password',
            'label' => 'Password',
            'rules' => 'trim|matches[password_confirm]'
        ),
        'password_confirm' => array(
            'field' => 'password',
            'label' => 'Password',
            'rules' => 'trim|matches[password]'
        ),
    );

    public function get_new() {
        $user = new stdClass();
        $user->name = '';
        $user->email = '';
        $user->password = '';
        return $user;
    }

    public function do_login() {
        $user = $this->get_by(array(
            'email' => $this->input->post('email'),
            'password' => $this->hash($this->input->post('password'))
                ), TRUE);
        if (count($user)) {
            //log in user
            $data = array(
                'id' => $user->id,
                'name' => $user->name,
                'email' => $user->email,
                'loggedin' => TRUE
            );
            // var_dump($data);
            $this->session->set_userdata($data);
        }
    }

    public function do_logout() {

        $this->session->sess_destroy();
    }

    public function loggedin() {
        return (bool) $this->session->userdata('loggedin');
    }

    public function hash($string) {
        return hash('sha512', $string . config_item('encryption_key'));
    }

    public function _count() {
        $this->db->count_all($this->_table_name);
    }

}

?>
