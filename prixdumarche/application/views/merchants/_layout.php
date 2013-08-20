<!--head-->
<?php $this->load->view('components/page_head') ?>
<!--Header-->
<div class="container">
    <header>
        <div class="navbar navbar-fixed-top navbar-inverse hbar ">
            <div class="navbar-inner">
                <ul class="nav">
                    <li><?php echo anchor('merchant/dashboard', 'Dashboard'); ?></li>
                    <li><?php echo anchor('merchant/user', 'Profile'); ?></li>
                    <li><?php echo anchor('merchant/product', 'Products'); ?></li>
                </ul>
                <div class="nav pull-right">
                    <ul class="nav" >
                        <li> <a class="text-success"> <?php echo ucfirst($this->session->userdata('name')); ?> </a> </li>
                        <li><?php echo anchor('logout', 'Logout') ?></li>
                    </ul>
                </div>

            </div>
        </div>
    </header>
    <!-- content-->
    <div class="row-fluid">

        <div class="span6">
            <?php $this->load->view($subview); ?>
        </div>
    </div>
</div>
</div>
<!--tail-->
<?php echo $this->load->view('components/page_tail') ?>

