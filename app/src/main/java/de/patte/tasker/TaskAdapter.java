package de.patte.tasker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Patte on 17.06.16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> taskList;
    private TaskDbHelper dbHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, dueTo;
        public CheckBox isDone;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            isDone = (CheckBox) view.findViewById(R.id.isDone);
            dueTo = (TextView) view.findViewById(R.id.dueTo);
        }
    }

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void removeTask(int position) {
        this.dbHelper.removeTask(taskList.get(position));
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_row, parent, false);

        this.dbHelper = new TaskDbHelper(parent.getContext());

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.isDone.setChecked(task.isDone() == 1 ? true : false);
        holder.dueTo.setText("12.12.12");

        holder.isDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                task.setDone(task.isDone() == 1 ? 0 : 1);

                dbHelper.updateTask(task);

                Toast.makeText(v.getContext(), "Clicked on Checkbox: " + task.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
