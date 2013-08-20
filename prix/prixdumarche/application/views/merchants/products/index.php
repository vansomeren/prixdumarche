<div class='span6'>
    <div>
        <h4> Total Products:  <?php echo count($product); ?> </h4>
        <div class="pull-right"> <?php echo anchor('merchant/product/edit', '<i class="icon-plus"></i> Add Product'); ?></div>
    </div>
    <table class="table table-bordered table-condensed table-hover">
        <?php if (count($product)): ?>

            <tr>
                <th>Thumb</th>
                <th>Product Name</th>

                <th>Product Description</th>
                <th>Price</th>
                <th>Date Added</th>

                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <?php foreach ($product as $item): ?>
                <tr>
                    <td><img src="<?php base_url() ?>/images/holder.js/94x94"></td>
                    <td><?php echo anchor('merchant/product/edit/' . $item->id, $item->title); ?></td>

                    <td> <?php echo $item->description ?></td>
                    <td> <?php echo $item->price ?></td>
                    <td> <?php echo $item->pubdate ?></td>
                    <td><?php echo bt_edit('merchant/product/edit/' . $item->id); ?></td>
                    <td><?php echo bt_delete('merchant/product/delete/' . $item->id); ?></td>

                </tr>
            <?php endforeach; ?>

        <?php else: ?>
            <br>
            <?php
            echo '<pre class="alert alert-error"><h5>You have not placed any products . </h5></pre>'
            . '<br>' .
            '<pre class="alert alert-info">click &nbsp;<b>"' . anchor('merchant/product/edit', ' Add Product') . '"</b> to place new products.</pre>'
            ?>
        <?php endif; ?>

    </table>
</div>
<div class='span6'>
    <legend>Current Category </legend>
    <>

</div>