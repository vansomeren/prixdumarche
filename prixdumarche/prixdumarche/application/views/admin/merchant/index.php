<div class="container">
    <div class="row-fluid">
        <div class="span6">
            <h4> Merchants</h4>
        <table class="table table-bordered ">
    <?php if (count($merchants)): ?>
        <tr>
            <th> ID</th>
               <th> Name</th>
                <th>Join Date</th>
                <th>profile</th>
        </tr>
        <?php foreach ($merchants as $merchant): ?>
            <tr>
                <td><?php echo $merchant->id; ?> </td>
                <td><?php echo $merchant->name; ?> </td>
                <td> <?php echo $merchant->created ?> </td>;
                <td><?php echo '<button class="btn btn-info">' . bt_view('admin/merchant/view/' . $merchant->id) . '</button>' ?></td>
            </tr>
        <?php endforeach; ?>

    <?php endif; ?>
        </table>
        </div>
    </div>
</div>
