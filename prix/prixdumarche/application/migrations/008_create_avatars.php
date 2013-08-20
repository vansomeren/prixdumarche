<?php

class Migration_Create_Avatars extends CI_Migration {

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
                'constraint' => '100',
            ),
            'url' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
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

        $this->dbforge->create_table('avatars');


        //create a column for the foreign key
        $this->db->query('ALTER TABLE `avatars` ADD COLUMN `user_id` int(11) UNSIGNED DEFAULT NULL;');
        //Add Foreign key and set contraint
        $this->db->query('ALTER TABLE `avatars` ADD CONSTRAINT `fk_user_avatar`  FOREIGN KEY(`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
        $this->db->query('ALTER TABLE `avatars` ADD COLUMN `merchant_id` int(11) UNSIGNED DEFAULT NULL;');
        //Add Foreign key and set contraint
        $this->db->query('ALTER TABLE `avatars` ADD CONSTRAINT `fk_merchant_avatar`  FOREIGN KEY(`merchant_id`) REFERENCES merchants(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('avatars');
    }

}

?>
