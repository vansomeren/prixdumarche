<?php

/*
 * Create the table for the table
 */

class Migration_Create_Products extends CI_Migration {

    //create the table
    public function up() {
        $fields = array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 11,
                'unsigned' => TRUE,
                'auto_increment' => TRUE
            ),
            'title' => array(
                'type' => 'TEXT',
            ),
            'description' => array(
                'type' => 'TEXT',
            ),
            'price' => array(
                'type' => 'DOUBLE',
                'default' => 0.00
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
        #create table if does not exist
        $this->dbforge->create_table('products', TRUE);
        /*
         * merchant_id
         * create a column for the foreign key
         * Add Foreign key and set contraint
         */
        $this->db->query('ALTER TABLE `products` ADD COLUMN `merchant_id` int(11) UNSIGNED DEFAULT NULL;');

        $this->db->query('ALTER TABLE `products` ADD CONSTRAINT `fk_product_merchant`  FOREIGN KEY(`merchant_id`) REFERENCES merchants(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');

        /*
         * category_id
         * create a column for the foreign key
         * Add Foreign key and set contraint
         */
        $this->db->query('ALTER TABLE `products` ADD COLUMN `category_id` int(11) UNSIGNED DEFAULT NULL;');
        $this->db->query('ALTER TABLE `products` ADD CONSTRAINT `fk_product_category`  FOREIGN KEY(`category_id`) REFERENCES categories(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
        /*
         * location_id
         * create a column for the foreign key
         * Add Foreign key and set contraint
         */
        $this->db->query('ALTER TABLE `products` ADD COLUMN `location_id` int(11) UNSIGNED DEFAULT NULL;');
        $this->db->query('ALTER TABLE `products` ADD CONSTRAINT `fk_product_location`  FOREIGN KEY(`location_id`) REFERENCES location(`id`) ON DELETE CASCADE ON UPDATE CASCADE;');
    }

    public function down() {
        $this->dbforge->drop_table('products');
    }

}

?>
