package com.sepidehmiller.android_task_tracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.sepidehmiller.android_task_tracker.TaskDbSchema.*;

/**
 * Created by baghaii on 5/24/16.
 */
public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TaskTable.NAME + "(" +
                TaskTable.Cols.ID + ", " +
                TaskTable.Cols.TITLE + ", " +
                TaskTable.Cols.CREATION_DATE + ", " +
                TaskTable.Cols.DUE_DATE + ", " +
                TaskTable.Cols.DESCRIPTION + ", " +
                TaskTable.Cols.PRIORITY +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
