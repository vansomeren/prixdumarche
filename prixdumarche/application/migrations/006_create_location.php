<?php

/*
 * Create the table for the table
 */

class Migration_Create_Location extends CI_Migration {

    //create the table fields
    public function up() {
        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'place' => array(
                'type' => 'TEXT',
                'null' => TRUE
            ),
            'slug' => array(
                'type' => 'TEXT',
                'null' => TRUE
            ),
            'created' => array(
                'type' => 'DATETIME',
            ),
            'modified' => array(
                'type' => 'DATETIME',
            )
        );

        $this->dbforge->add_field($fields); //insert the fields
        $this->dbforge->add_key('id', TRUE); //set primary key
        #create table if does not exist
        $this->dbforge->create_table('location', TRUE); //create table
        //create a column for the foreign key
        //$this->db->query('ALTER TABLE `location` ADD COLUMN `product_id` int(11) UNSIGNED DEFAULT NULL;');
        //Add Foreign key and set contraint
//        $this->db->query('ALTER TABLE `location` ADD CONSTRAINT `fk_product_location`  FOREIGN KEY(`product_id`) REFERENCES products(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('location');
    }

}
?>

