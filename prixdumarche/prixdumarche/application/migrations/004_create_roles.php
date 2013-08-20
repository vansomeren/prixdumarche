<?php

class Migration_Create_Roles extends CI_Migration {

    public function up() {

        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'role' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'defualt' => 'Not Set',
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

        $this->dbforge->create_table('roles');


        //create a column for the foreign key
        $this->db->query('ALTER TABLE `roles` ADD COLUMN `u_id` int(11) UNSIGNED DEFAULT NULL;');
        //Add Foreign key and set contraint
        $this->db->query('ALTER TABLE `roles` ADD CONSTRAINT `fk_rolesuser`  FOREIGN KEY(`u_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
        $this->db->query('ALTER TABLE `roles` ADD COLUMN `m_id` int(11) UNSIGNED DEFAULT NULL;');
        //Add Foreign key and set contraint
        $this->db->query('ALTER TABLE `roles` ADD CONSTRAINT `fk_rolesmerchant`  FOREIGN KEY(`m_id`) REFERENCES merchants(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('roles');
    }

}
?>

