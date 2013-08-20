<?php

class Category_Model extends MY_Model {

    protected $_table_name = 'categories';
    protected $_primary_key = 'id';
    protected $_order_by = 'slug';
    protected $_timestamps = True;

    public function get_new() {
        $category = new stdClass();
        $category->tpye = '';
        $category->slug = '';
        $category->created = '';
        $category->modified = '';
        return $category;
    }

    public $rules = array(
        'pubdate' => array(
            'field' => 'pubdate',
            'label' => 'Publication date',
            'rules' => 'trim|required|exact_length[10]|xss_clean'
        ),
        'type' => array(
            'field' => 'title',
            'label' => 'Title',
            'rules' => 'trim|required|max_length[100]|xss_clean'
        ),
        'slug' => array(
            'field' => 'slug',
            'label' => 'Slug',
            'rules' => 'trim|required|max_length[100]|url_title|xss_clean'
        ),
    );

    public function set_published() {
        $this->db->where('pubdate <=', date('Y-m-d'));
    }

    public function get_recent($limit = 3) {

        // Fetch a limited number of recent articles
        $limit = (int) $limit;
        $this->set_published();
        $this->db->limit($limit);
        return parent::get();
    }

}

?>
