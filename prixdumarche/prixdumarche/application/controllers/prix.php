<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Prix extends Frontend_Controller {

    /**
     * Index Page for this controller.
     * Since this controller is set as the default controller in
     * config/routes.php, it's displayed at http://example.com/
     *
     * So any other public methods not prefixed with an underscore will
     * map to /index.php/welcome/<method_name>
     * @see http://codeigniter.com/user_guide/general/urls.html
     */
    public function index() {

        //load the view
        $this->data['subview'] = 'subviews/index'; //search view
        $this->load->view('main_layout', $this->data);
    }

}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */