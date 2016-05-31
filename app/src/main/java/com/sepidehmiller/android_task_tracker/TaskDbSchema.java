package com.sepidehmiller.android_task_tracker;

/**
 * Created by baghaii on 5/24/16.
 */
public class TaskDbSchema {
    public static final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String CREATION_DATE = "creation_date";
            public static final String DUE_DATE = "due_date";
            public static final String DESCRIPTION = "description";
            public static final String PRIORITY = "priority";
        }
    }
}
