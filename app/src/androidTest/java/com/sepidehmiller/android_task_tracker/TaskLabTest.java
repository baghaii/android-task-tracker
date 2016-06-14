package com.sepidehmiller.android_task_tracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by baghaii on 6/13/16.
 */
public class TaskLabTest {

    private static final String DB_NAME = "TaskTest.db";
    private TaskLab taskLab;

    @Before
    public void setupDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        taskLab = TaskLab.get(context, DB_NAME);
    }

    @Test
    public void addTask_regularTasktoDb() {
        assertEquals("Should have no tasks before first task", 0, taskLab.getTasks().size());
        taskLab.addTask(new Task());
        assertEquals("Should have one task after adding a task", 1, taskLab.getTasks().size());
    }

    @After
    public void teardownDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        context.deleteDatabase(DB_NAME);
    }
}