<?php

class Products_Model extends MY_Model {

    function __construct() {
        parent::__construct();
    }

    protected $_table_name = 'products';
    protected $_primary_key = 'id';
    protected $_order_by = 'pubdate desc, id desc';
    protected $_timestamps = True;
    public $rules = array(
        'pubdate' => array(
            'field' => 'pubdate',
            'label' => 'Publication date',
            'rules' => 'trim|required|exact_length[10]xss_clean'
        ),
        'title' => array(
            'field' => 'title',
            'label' => 'Product Title',
            'rules' => 'trim|required|xss_clean'
        ),
        'category' => array(
            'field' => 'category',
            'label' => 'Category',
            'rules' => 'trim|required|xss_clean'
        ),
        'description' => array(
            'field' => 'description',
            'label' => 'Description',
            'rules' => 'trim|required|xss_clean'
        ),
        'price' => array(
            'field' => 'price',
            'label' => 'Price',
            'rules' => 'trim|required|xss_clean'
        ),
    );

    public function get_new() {
        $product = new stdClass();
        $product->title = '';

        $product->description = '';
        $product->price = '';
        $product->merchant_id = ' ';
        $product->category_id = '';

        $product->pubdate = date('Y-m-d');
        return $product;
    }

    public function set_published() {
        $this->db->where('pubdate <=', date('Y-m-d'));
    }

    public function get_recent($limit = 3) {
        $limit = (int) $limit;
        $this->set_published();
        $this->db->limit($limit);
        return parent::get();
    }

}

?>
