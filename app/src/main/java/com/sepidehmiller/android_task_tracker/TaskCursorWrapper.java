package com.sepidehmiller.android_task_tracker;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

import static com.sepidehmiller.android_task_tracker.TaskDbSchema.*;

/**
 * Created by baghaii on 5/31/16.
 */
public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String idString = getString(getColumnIndex(TaskTable.Cols.ID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        long dueDate = getLong(getColumnIndex(TaskTable.Cols.DUE_DATE));
        String description = getString(getColumnIndex(TaskTable.Cols.DESCRIPTION));
        String priorityString = getString(getColumnIndex(TaskTable.Cols.PRIORITY));
        long createDate = getLong(getColumnIndex(TaskTable.Cols.CREATION_DATE));

        Task task = new Task(Integer.parseInt(idString));
        task.setTitle(title);
        task.setDueDate(new Date(dueDate));
        task.setDescription(description);
        task.setPriority(Integer.parseInt(priorityString));
        task.setCreateDate(new Date(createDate));

        return task;
    }
}
