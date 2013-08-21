<?php

class Search_Model extends MY_Model {

    function __construct() {
        parent::__construct();
    }

    public function search($keyword, $limit, $offset) {
        //result query
        $this->db->select(" p.id, p.title, p.description,p.price, p.merchant_id,p.category_id, m.id,
            m.name,m.email,m.phone")
                ->from('products p')
                ->join('merchants m', 'm.id = p.merchant_id', 'inner')
                ->like('p.title', $keyword, 'match')
                ->or_like('p.description', $keyword, 'match')
                ->or_like('m.name', $keyword, 'match')
                ->limit($limit, $offset);
        $this->data['query'] = $this->db->get();
        $this->data['count'] = $this->db->count_all_results();

        return $this->data['query']->result_array();
    }

}

?>
