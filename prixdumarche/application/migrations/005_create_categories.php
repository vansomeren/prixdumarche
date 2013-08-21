<?php

class Migration_Create_Categories extends CI_Migration {

    public function up() {

        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'type' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
            ),
            'slug' => array(
                'type' => 'TEXT',
                'null' => TRUE
            ),
            'pubdate' => array(
                'type' => 'DATE',
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

        $this->dbforge->create_table('categories');
        //Add Foreign key and set contraint
        $this->db->query('ALTER TABLE `categories` ADD COLUMN `user_id` int(11) UNSIGNED DEFAULT NULL;');

        $this->db->query('ALTER TABLE `categories` ADD CONSTRAINT `fk_category_user`  FOREIGN KEY(`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('categories');
    }

}

?>
