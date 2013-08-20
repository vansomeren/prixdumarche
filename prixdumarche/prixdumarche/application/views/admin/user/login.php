<div class="span6 pull-right">
    <?php
    echo form_open('admin')
    . form_fieldset($title, array('form-signin-heading'))
    . '<pre class="alert alert-info"><b>' . $p . '</b></pre>'
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
    <div ><?php echo $this->session->flashdata('error') ?></div>
    <div class="control-group">
        <?php
        echo form_submit('submit', 'sign in', $extra = 'class="btn btn-primary"')
        ?>
    </div>
    <?php
    echo form_fieldset_close() . form_close();
    ?>
</div>
