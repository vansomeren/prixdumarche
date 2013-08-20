<?php

class Migration_Create_Images extends CI_Migration {

    public function up() {

        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'title' => array(
                'type' => 'VARCHAR',
                'constraint' => '160',
            ),
            'url' => array(
                'type' => 'VARCHAR',
                'constraint' => '160',
                'unique' => true,
            ),
            'created' => array(
                'type' => 'DATETIME',
            ),
            'modified' => array(
                'type' => 'DATETIME',
            )
        );
        $this->dbforge->add_field($fields);
        $this->dbforge->add_key('id', TRUE);

        $this->dbforge->create_table('images');

        /*
         * image_id
         * create a column for the foreign key
         * Add Foreign key and set contraint
         */

        $this->db->query('ALTER TABLE `images` ADD COLUMN `product_id` int(11) UNSIGNED DEFAULT NULL;');
        $this->db->query('ALTER TABLE `images` ADD CONSTRAINT `fk_product_image`  FOREIGN KEY(`product_id`) REFERENCES products(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('images');
    }

}

?>
