<div class="container">
    <div class="row-fluid">
        <div class="span4">
            <div class="pull-right">
                <?php
                echo anchor('admin/merchant', 'back'); ?>
                </div>
                <?php foreach ($merchant as $row) : ?>
                <h4>Merchant:<?php echo ucfirst($row->name) ?></h4>
            <table class="table table-bordered">



        <?php
         echo '<tr>';
    echo'<td> <img src="<?php base_url() ?>/images/holder.js/180x120"></td>';
    echo '<tr><td><b>Name</b>:' . ucfirst($row->name) . '</td></tr>';

        echo '<tr><td><b>Email</b>:<a>' . $row->email . '</a></td></tr>';
        echo '<tr><td><b>Phone</b>:' . $row->phone . '</td></tr>';
        echo '<tr><td><b>Last Edit</b>:' . $row->modified . '</td></tr>';
        echo'</tr>'
        ?>
                <?php
    endforeach;
    ?>
        </table>
        </div>
        <div class="span4">
            <h4>Products:&nbsp;<?php echo count($product); ?>
            </h4>
                <table class="table table-bordered">
                <th>Title</th>
                <th>Price</th>

                <?php foreach ($product as $row) : ?>

                    <?php
                    echo '<tr>';
 echo '<tr>';
                    echo '<td>' . ucfirst($row->title) . '</td>';
                    echo '<td>' . ($row->price) . '</td>';

    echo'</tr>';

                    ?>
                    <?php
                endforeach;
                ?>
            </table>
        </div>
    </div>
</div>