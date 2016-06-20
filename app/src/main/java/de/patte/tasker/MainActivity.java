package de.patte.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Task> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private FloatingActionButton fab;
    private TaskDbHelper taskDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        taskAdapter = new TaskAdapter(taskList);
        RecyclerView.LayoutManager taskLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(taskLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taskAdapter);

        fab = (FloatingActionButton) findViewById(R.id.add_new_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Ready to add new task", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), NewTask.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper.Callback callback = new TaskTouchHelper(taskAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);

        helper.attachToRecyclerView(recyclerView);

        taskDbHelper = new TaskDbHelper(this);
        prepareTaskData();
    }

    private void prepareTaskData() {

        /*final String loremIpsum = "Lorem ipsum dolor sit amet,";

        for(int i = 0; i < 100; i++) {
            Task task = new Task(loremIpsum, false);
            taskList.add(task);
        }*/

       // taskList = taskDbHelper.getAllTasks();
        taskList.addAll(taskDbHelper.getAllTasks());

        Log.d(TAG, "prepareTaskData: " + taskList.toString());

        taskAdapter.notifyDataSetChanged();
    }
}
