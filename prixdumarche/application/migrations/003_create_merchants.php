<?php

class Migration_Create_Merchants extends CI_Migration {

    //create the table
    public function up() {
        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'name' => array(
                'type' => 'VARCHAR',
                'constraint' => '100'
            ),
            'email' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'unique' => true
            ),
            'password' => array(
                'type' => 'VARCHAR',
                'constraint' => '128',
            ),
            'phone' => array(
                'type' => 'REAL',
                'default' => '0',
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
        #create table if does not exist
        $this->dbforge->create_table('merchants', TRUE);
    }

    public function down() {
        $this->dbforge->drop_table('merchants');
    }

}

?>
