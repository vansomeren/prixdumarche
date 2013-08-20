<?php $this->load->view('components/page_head'); ?>
<header>
    <div class="navbar navbar-fixed-top navbar-inverse hbar ">
        <div class="navbar-inner">
    <!--  <a class="brand" ahref="<?php //echo site_url('/')              ?>"><? //echo $meta_title             ?></a>-->
            <?php echo anchor('/', $meta_title, 'class="brand"') ?>
            <ul class="nav">
                <li><?php echo anchor('signup', 'Sign up'); ?></li>
                <li><?php // merchant/users/createecho anchor('#', 'Link');                                  ?></li>
                <li><?php //echo anchor('#', 'Link');           ?></li>
                <li><?php //echo anchor('#', 'Link');           ?></li>
                <li><?php //echo anchor('#', 'Link');           ?></li>
            </ul>
            <div class="nav pull-right ">
                <ul class="nav" >
                    <li><?php echo anchor('signin', 'Login') ?></li>
                </ul>
            </div>

        </div>
    </div>
</header>

<div class="container">
    <div class="row">
        <?php $this->load->view($subview); ?>
    </div>
</div>

<footer>
    <?php $this->load->view('components/page_tail'); ?>
</footer>