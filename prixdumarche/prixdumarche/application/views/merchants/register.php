<?php
/*
 * author: Lee
 * title: Register page
 * Description: Create your Merchant Account.
 */
?>
<!-- * required fields-->
<style>
    .ast{ color: red; }
</style>
<div class="span6">

    <?php
    echo form_open('signup')
    . form_fieldset('Create your Merchant Account', array('form-signin-heading'));
    ?>

    <div>
        <?php
        echo form_label('Name:<i class="ast">*</i>', 'name', array('class="control-label"')) . form_error('name')
        . form_input('name');
        ?>
    </div>
    <div>
        <?php
        echo form_label('Email:<i class="ast">*</i>', 'email', array('class="control-label"')) . form_error('email') . form_input('email');
        ?>
    </div>
    <div>
        <?php
        echo form_label('Phone:<i class="ast">*</i>', 'phone', array('class="control-label"')) . form_error('phone') . form_input('phone');
        ?>
    </div>
    <div>
        <?php
        echo form_label('Password:<i class="ast">*</i>', 'password', array('class="control-label"'))
        . form_password('password') . form_error('password');
        ?>
    </div>
    <div>
        <?php
        echo form_label('Confirm Password:<i class="ast">*</i>', 'confirm_password', array('class="control-label"')) .
        form_password('confirm_password') . form_error('confirm_password');
        ?>
    </div>
    <div class="control-group">
        <?php
        echo form_submit('submit', 'sign up', $extra = 'class="btn btn-primary"')
        ?>
    </div>
    <?php
    echo form_fieldset_close() . form_close();
    ?>
    <pre class="span2 alert-info"> <i class="ast">*</i> Required.</pre><br>
</div>