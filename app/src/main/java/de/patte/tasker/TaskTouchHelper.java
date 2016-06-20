package de.patte.tasker;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Patte on 20.06.16.
 */
public class TaskTouchHelper extends ItemTouchHelper.SimpleCallback {

    private TaskAdapter taskAdapter;

    public TaskTouchHelper(TaskAdapter taskAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.taskAdapter = taskAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        this.taskAdapter.removeTask(viewHolder.getAdapterPosition());
    }
}
