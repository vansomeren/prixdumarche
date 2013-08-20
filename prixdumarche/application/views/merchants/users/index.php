<div class="container">
    <div class="row-fluid">
        <div class="span3">
            <div>
                <h4>Profile</h4>
                <div class="span1 pull-right">
                    <?php echo anchor('merchant/user/edit', '<i class="icon-edit"></i> Edit'); ?>
                </div>
            </div>
            <table class="table table-bordered">
                <?php foreach ($merchant as $user): ?>

                    <?php
                    echo '<tr>';
                    echo'<tr>' . '<img src="<?php base_url() ?>/images/holder.js/180x120">' . '</tr>';
                    echo '<tr><td><b>Name</b>:' . ucfirst($user->name) . '</td></tr>';
                    echo '<tr><td><b>Email</b>:<a>' . $user->email . '</a></td></tr>';
                    echo '<tr><td><b>Phone</b>:' . $user->phone . '</td></tr>';
                    echo '</tr>'
                    ?>
                <?php endforeach; ?>
            </table>
        </div>
    </div>
</div>