<div class="span4 pull-right">
    <?php
    echo form_open('signin')
    . form_fieldset('Login', array('form-signin-heading'));
    ?>
    <div>
        <?php
        echo form_label('Email:', 'email', array('class="control-label"')) . form_error('email') .
        form_input('email');
        ?>
    </div>
    <div>
        <?php
        echo form_label('Password:', 'password', array('class="control-label"'))
        . form_password('password');
        ?>
    </div>
    <div class="control-group">
        <?php
        echo form_submit('submit', 'sign in', $extra = 'class="btn btn-primary"')
        ?>
    </div>
    <?php
    echo form_fieldset_close() . form_close();
    ?>
</div>
