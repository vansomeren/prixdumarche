<div class="container">
    <div class="row-fluid">
        <div class="span6">
            <div>
                <h4 class="text-info"><?php echo empty($product->id) ? 'Add a new product' : 'Edit product details : &nbsp;' . ucfirst($product->title) ?></h4>
                <div class="pull-right">
                    <?php echo anchor('merchant/product', 'Back') ?>
                </div>

            </div>
            <table class="table ">

                <?php echo form_open() ?>
                <?php echo form_hidden('merchant_id', set_value('merchant_id', $this->session->userdata('id'))); ?>
                <tr>
                    <td>
                        <label>Publication Date:</label></td>
                    <td>
                        <?php echo form_input('pubdate', $product->pubdate, 'class="datepicker"') . form_error('pubdate');
                        ?>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Name:</label></td>
                    <td>
                        <?php echo form_input('name', set_value('name', $product->title)) . form_error('name'); ?>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>Price:</label></td>
                    <td>
                        <?php echo form_input('price', set_value('description', $product->price)) . form_error('price');
                        ?>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Description:</label></td>
                    <td>
                        <?php
                        echo form_textarea('description', set_value('description', $product->description)) . form_error('description');
                        ?>
                    </td>
                </tr>

            </table>
            <div class="form-actions">
                <?php echo form_submit('submit', 'save', 'class="btn btn-primary"'); ?>

            </div>
            <?php echo form_close(); ?>
        </div>
        <div class="span6">
            <legend>Categories Available</legend>
            <ol>
                <?php
                foreach ($category as $row) :

                    echo '<li>' . $row->type . '</li>';
                    ?>

                <?php endforeach; ?>
            </ol>
        </div>
    </div>
</div>

<script>
    $(function() {
        $('.datepicker').datepicker({format: 'yyyy-mm-dd'});
    }
    );
</script>