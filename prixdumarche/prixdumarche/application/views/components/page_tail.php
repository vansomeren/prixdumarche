<footer>
    <div class="navbar navbar-fixed-bottom">
        <div id="footer">
            <div class="container">
                <center>
                    <p class="muted credit">
                        <?php
                        echo '&copy;&nbsp;' . date('Y') . '&nbsp;';
                        echo anchor('/', 'PrixDuMarche');
                        ?>
                    </p>
                </center>
            </div>
        </div>
    </div>

    <script src="<?php echo base_url() ?>js/bootstrap.min.js"></script>
    <script src="<?php echo base_url() ?>js/holder.js"></script>
</footer>
</body>
</html>