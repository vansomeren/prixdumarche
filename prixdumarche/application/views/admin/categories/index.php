<section>
    <legend><h3>Current Category</h3></legend>
    <?php echo anchor('admin/category/edit', '<i class="icon-plus"></i> Add a category'); ?>
    <table class="table table-striped">

        <thead>
            <tr>

                <th>Title</th>
                <th>Parent</th>
                <th>Slug</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <?php if (count($categories)): foreach ($categories as $category): ?>

                    <tr>

                        <td><?php echo anchor('admin/category/edit/' . $category->id, $category->title); ?></td>
                        <td><?php echo $category->parent ?></td>
                        <td><?php echo $category->slug ?></td>
                        <td><?php echo bt_edit('admin/category/edit/' . $category->id); ?></td>
                        <td><?php echo bt_delete('admin/category/delete/' . $category->id); ?></td>

                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="3"> No categories found.</td>
                </tr>
            <?php endif; ?>
        </tbody>

    </table>
</section>
