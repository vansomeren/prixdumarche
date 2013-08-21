
<!--head-->
<?php $this->load->view('components/page_head') ?>
<br><br>
<!--Header-->
<div class="navbar navbar-fixed-top navbar-inverse hbar ">
    <div class="navbar-inner">
        <?php echo anchor('/', $meta_title, 'class="brand"') ?>
        <!--        <div class="nav pull-right ">
                    <ul class="nav" >
                        <li></li>
                    </ul>
                </div>-->

    </div>
</div>
<!-- content-->
<div class="container-fluid">
    <br>
    <div class="row-fluid">
        <div class="span10 ">
            <div class="span7">
                <?php $this->load->view($subview); ?>
            </div>
            <div class="span3">
                .
            </div>
        </div>
    </div>
</div>
<!--Footer-->
<?php echo $this->load->view('components/page_tail') ?>
</body>
