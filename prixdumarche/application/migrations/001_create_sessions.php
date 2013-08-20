<?php

class Migration_Create_Sessions extends CI_Migration {

    public function up() {
        $fields = array(
            'session_id' => array(
                'type' => 'VARCHAR',
                'constraint' => '45',
            ),
            'ip_address' => array(
                'type' => 'VARCHAR',
                'constraint' => '45',
            ),
            'user_agent' => array(
                'type' => 'VARCHAR',
                'constraint' => '120',
            ),
            'last_activity' => array(
                'type' => 'INT',
                'constraint' => '10',
                'unsigned' => TRUE,
            ),
            'user_data' => array(
                'type' => 'TEXT',
                'constraint' => '10',
            ),
        );
        $this->dbforge->add_field($fields);
        $this->dbforge->add_key('session_id', TRUE);

        $this->dbforge->create_table('ci_sessions', TRUE);
        $this->db->query('ALTER TABLE `ci_sessions` ADD KEY `last_activity_idx`(`last_activity`)');
    }

    public function down() {
        $this->dbforge->drop_table('ci_sessions');
    }

}
?>


<!--CREATE TABLE IF NOT EXISTS  `ci_sessions` (
        session_id varchar(40) DEFAULT '0' NOT NULL,
        ip_address varchar(45) DEFAULT '0' NOT NULL,
        user_agent varchar(120) NOT NULL,
        last_activity int(10) unsigned DEFAULT 0 NOT NULL,
        user_data text NOT NULL,
        PRIMARY KEY (session_id),
        KEY `last_activity_idx` (`last_activity`)
);-->