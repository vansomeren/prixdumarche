
<div class="span4 well">
    <div class="nav-header">Menu</div>
    <ul class="nav nav-list nav-stacked">
        <li><?php echo anchor('merchant/user', '<i class="icon-eye-open"> </i>  profile '); ?></li>
        <li><?php echo anchor('merchant/product', '<i class="icon-folder-close"> </i>  products '); ?></li>
    </ul>
</div>
<div class="span4">
    <table class="table table-bordered">

        <tr>
            <th>Product Count</th>
            <?php if (count($product) != 0): ?>
                <td><?php echo anchor('merchant/product', (count($product))) ?></td>
            <?php else: ?>
                <td><?php echo '00' ?></td>
            <?php endif; ?>
        </tr>
    </table>
</div>
<div class="span4">
    ...
</div>