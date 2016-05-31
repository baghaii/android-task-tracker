package com.sepidehmiller.android_task_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.sepidehmiller.android_task_tracker.TaskDbSchema.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by baghaii on 5/11/16.
 */
public class TaskLab {
    private static TaskLab sTaskLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }

        return sTaskLab;
    }

    private TaskLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addTask(Task t) {
        ContentValues values = getContentValues(t);
        mDatabase.insert(TaskTable.NAME, null, values);
    }

    public void removeTask(Task t) {
        mDatabase.delete(TaskTable.NAME, TaskTable.Cols.ID + " = ?",
                new String[] { Integer.toString(t.getId()) });
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tasks;
    }

    public Task getTask(int id) {

        TaskCursorWrapper cursor = queryTasks(
                TaskTable.Cols.ID + " = ?",
                new String[] {Integer.toString(id)}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void updateTask(Task t) {
        String idString = Integer.toString(t.getId());
        ContentValues values = getContentValues(t);

        mDatabase.update(TaskTable.NAME, values,
                TaskTable.Cols.ID + "=?", new String[] { idString });
    }

    private static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskTable.Cols.ID, Integer.toString(task.getId()));
        values.put(TaskTable.Cols.TITLE, task.getTitle());
        values.put(TaskTable.Cols.CREATION_DATE, task.getCreateDate().getTime());
        values.put(TaskTable.Cols.DUE_DATE, task.getDueDate().getTime());
        values.put(TaskTable.Cols.DESCRIPTION, task.getDescription());
        values.put(TaskTable.Cols.PRIORITY, Integer.toString(task.getPriority()));

        return values;

    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null,  //groupBy
                null,  //having
                null   //orderBy
        );

        return new TaskCursorWrapper(cursor);
    }
 }
