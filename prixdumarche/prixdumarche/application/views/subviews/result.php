<div class="container">
    <div class="row-fluid">
        <div class="span3">
            <h5>Search Category</h5>
            <form class="form-search">
                <div class = "input-append">
                    <input type = "text" class = "span8 search-query" name = "keyword">
                    <button type = "submit" class = "btn btn-primary">Search</button>
                </div>
            </form>
        </div>
        <div class="span3">
            <?php if (count($items)): ?>
                <table>
                    <?php
                    foreach ($items as $item) :
                        ?>
                        <tr class="controls-row">

                            <td><img src = "<?php base_url() ?>/images/holder.js/120x120"></td>
                            <td>
                                <b>Title:</b><?php echo $item["title"]; ?>
                                <br>
                                <b>Price:</b> <?php echo $item["price"]; ?> <br>
                                <b>Merchant</b>:  <?php echo'<span class="text text-info">' . ucfirst($item["name"]) . '</span>'; ?><br>
                                <b>Email</b>: <?php echo '<a>' . $item['email'] . '</a>'; ?>  <br>
                                <b>Phone</b>: <?php echo $item["phone"]; ?>
                            </td>

                        </tr>
                    <?php endforeach; ?>
                </table>
            <?php endif; ?>
        </div>
    </div>
</div>