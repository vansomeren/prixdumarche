
<section>
    <div>
        <div class="pull-right">
            <?php echo anchor('merchant/user/', 'Back') ?>
        </div>
        <?php foreach ($fields as $field): ?>

            <h4>
                <?php echo 'Edit Profile: &nbsp; ' . ucfirst($field->name) ?>
            </h4>
        </div>
        <table>

            <?php echo form_open() ?>
            <tr>
                <td> <?php echo form_label('Full Name:') ?></td>
                <td> <?php echo form_input('name ', set_value('name ', $field->name)) . form_error('name') ?></td>
            </tr>
            <tr>
                <td> <?php echo form_label('Email Address:') ?></td>
                <td> <?php echo form_input('email ', set_value('email ', $field->email)) . form_error('email') ?></td>
            </tr>

            <tr>
                <td>
                    <?php echo form_label('Password:') ?></td>
                <td>
                    <?php echo form_password('password') . form_error('password') ?>
                </td>
            </tr>
            <tr>
                <td>
                    <?php echo form_label('Password Confirm:') ?></td>
                <td>
                    <?php echo form_password('confirm_password') . form_error('confirm_password') ?>
                </td>
            </tr>
            <tr>
                <td> <?php echo form_label('Phone:') ?></td>
                <td> <?php echo form_input('phone ', set_value('phone ', $field->phone)) . form_error('phone') ?></td>
            </tr>

        </table>
        <div class="form-actions">
            <?php echo form_submit('submit ', 'save ', 'class = "btn btn-primary"') ?>
        </div>
        <?php echo form_close(); ?>
    <?php endforeach; ?>

</section>
