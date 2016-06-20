package de.patte.tasker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Patte on 19.06.16.
 */
public class TaskDataSource {

    private SQLiteDatabase database;
    private TaskDbHelper dbHelper;

    public TaskDataSource(Context context) {
        dbHelper = new TaskDbHelper(context);
    }
}
