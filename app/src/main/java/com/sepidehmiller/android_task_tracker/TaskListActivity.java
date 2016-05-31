package com.sepidehmiller.android_task_tracker;

import android.support.v4.app.Fragment;

/**
 * Created by baghaii on 5/12/16.
 */
public class TaskListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}
