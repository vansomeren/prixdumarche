
<section>
    <div>
        <div class="pull-right">
            <?php echo anchor('admin/merchant', 'Back') ?>
        </div>
        <h3><?php echo empty($merchant->id) ? 'Add a new merchant' : 'Edit merchant information : &nbsp;' . ucfirst($merchant->name) ?></h3>
    </div>
    <table class="table ">

        <?php echo form_open() ?>
        <tr>
            <td>
                <label>Name:</label></td>
            <td>
                <?php echo form_input('name', set_value('name', $merchant->name)) ?>
            </td>
        </tr>
        <tr>
            <td>
                <label>Email:</label></td>
            <td>
                <?php echo form_input('email', set_value('email', $merchant->email)) ?>
            </td>
        </tr>
        <tr>
            <td>
                <label>Password:</label></td>
            <td>
                <input type='password' name='password'>
            </td>
        </tr>
        <tr>
            <td>
                <label>Confirm Password:</label></td>
            <td>
                <input type='password' name='password_confirm'>
            </td>
        </tr>
        <?php echo validation_errors(); ?>
    </table>
    <div class="form-actions">
        <?php echo form_submit('submit', 'save', 'class="btn btn-primary"') ?>

    </div>
    <?php echo form_close() ?>
</div>

</section>
