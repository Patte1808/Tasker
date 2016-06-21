package de.patte.tasker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patte on 19.06.16
 */
public class TaskDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "TaskDbHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasker";
    private static final String TABLE_TASKS = "tasks";
    private static final String TASK_ID = "id";
    private static final String TASK_TITLE = "title";
    private static final String TASK_ISDONE = "isDone";

    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "(" + TASK_ID + " INTEGER PRIMARY KEY," + TASK_TITLE + " TEXT," + TASK_ISDONE + " INTEGER)";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASK_TITLE, task.getTitle());
        values.put(TASK_ISDONE, task.isDone());

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String filter = TASK_ID + "=" + task.getId();

        values.put(TASK_TITLE, task.getTitle());
        Log.d(TAG, "updateTask: Task is Done? " + task.isDone());
        values.put(TASK_ISDONE, task.isDone());

        db.update(TABLE_TASKS, values, filter, null);

        Log.d(TAG, "updateTask: Task " + task.getTitle() + " with id:x " + task.getId());
        db.close();
    }

    public void removeTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        String filter = TASK_ID + "=" + task.getId();

        Log.d(TAG, "removeTask: Task deleted with id " + task.getId());

        db.delete(TABLE_TASKS, filter, null);
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();

        String selectQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTitle(cursor.getString(1));
                task.setDone(Integer.parseInt(cursor.getString(2)));

                taskList.add(task);
            } while(cursor.moveToNext());
        }

        Log.d(TAG, "getAllTasks: Done getting tasks");

        return taskList;
    }
}
