package de.patte.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTask extends AppCompatActivity {

    private TextInputLayout titleWrapper;
    private TaskDbHelper taskDbHelper;
    private Button saveButton;
    private EditText taskTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titleWrapper = (TextInputLayout) findViewById(R.id.input_title_wrapper);
        titleWrapper.setHint(NewTask.this.getString(R.string.task_title));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.taskDbHelper = new TaskDbHelper(this);

        taskTitle = (EditText) findViewById(R.id.input_title);
        saveButton = (Button) findViewById(R.id.save_task);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(taskTitle.getText().toString(), "", 0);
                taskDbHelper.addTask(task);
                Intent intent = new Intent(NewTask.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}
