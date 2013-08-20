<?php

/*
 * title:Product Controller
 * Description:This handles the Product logic
 */

class Search extends Frontend_Controller {

    function __construct() {
        parent::__construct();

    }

    public function index() {
        $this->data['subview'] = 'subviews/index';
        $this->load->view('main_layout', $this->data);
    }

    public function product_search($keyword = NULL) {
        $limit = 10;
        $offset = 0;
        //get keyword
        $keyword = $this->input->post('keyword');
        //retrieve the result
       $this->data['items'] = $this->search_model->search($keyword, $limit, $offset);
        $this->data['count'] = count($this->data['items']);
        //load view
        $this->data['subview'] = 'subviews/result';
        
        //$this->load->view('test', $this->data);
        $this->load->view('main_layout', $this->data);
    }
    public function all() {

    }

}

?>
