package com.sepidehmiller.android_task_tracker;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by baghaii on 6/6/16.
 */


public class TaskTest {

    private static final String FAKE_TITLE = "Fake Title";

    @Test
    public void testTitle() {
        Task t = new Task();
        assertNull(t.getTitle());
        t.setTitle(FAKE_TITLE);
        assertEquals("Title set", FAKE_TITLE, t.getTitle());
    }
}
