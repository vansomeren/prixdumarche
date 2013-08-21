
<!--head-->
<style>
    .navnem{
        font-size: 12px;
        font-weight: bolder;
        font: small-caps;
        color: red;
    }
</style>
<?php $this->load->view('components/page_head') ?>
<div class="page-header">
    <div class="navbar navbar-fixed-top navbar-inverse hbar ">
        <div class="navbar-inner">
            <?php echo anchor('/', $meta_title, 'class="brand"') ?>
            <ul class="nav">
                <li><?php echo anchor('admin/dashboard', 'Dashboard'); ?></li>
                <li><?php echo anchor('admin/user', 'Users'); ?></li>
                <li><?php echo anchor('admin/merchant', 'Merchants'); ?></li>
                <li><?php echo anchor('admin/product', 'Products'); ?></li>
                <li><?php echo anchor('admin/category', 'category'); ?></li>
            </ul>
            <div class="nav pull-right ">
                <ul class="nav" >
                    <li><a class="navnem"><i class="icon-user icon-white"> </i> <?php echo ucfirst($this->session->userdata('name')) ?></a></li>
                    <li><?php echo anchor('admin/user/logout', 'Logout'); ?></li>
                </ul>
            </div>

        </div>
    </div>
</div>

<!-- content-->
<div class="container-fluid">
    <br>
    <div class="row-fluid">
        <div class="span10 ">

            <?php $this->load->view($subview); ?>


        </div>
    </div>
</div>
<!--Footer-->
<?php echo $this->load->view('components/page_tail') ?>
</body>
