<section>
    <legend><h3>Current Users</h3></legend>
    <?php echo anchor('admin/user/edit', '<i class="icon-plus"></i> Add a user'); ?>
    <table class="table table-striped">

        <thead>
            <tr>

                <th>Email</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <?php if (count($users)): foreach ($users as $user): ?>

                    <tr>
                        <td><?php echo anchor('admin/user/edit/' . $user->id, $user->email); ?></td>
                        <td><?php echo bt_edit('admin/user/edit/' . $user->id); ?></td>

                        <td><?php echo bt_delete('admin/user/delete/' . $user->id); ?></td>

                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="3"> No users found.</td>
                </tr>
            <?php endif; ?>
        </tbody>

    </table>
</section>
