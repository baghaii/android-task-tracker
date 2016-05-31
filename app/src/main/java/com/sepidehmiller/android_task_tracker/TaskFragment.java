package com.sepidehmiller.android_task_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by baghaii on 5/11/16.
 */
public class TaskFragment extends Fragment {

    private static final String ARG_TASK_ID = "task_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Task mTask;
    private EditText mTitleField;
    private Button mDueDateButton;
    private Button mDueTimeButton;
    private EditText mDescField;

    public static TaskFragment newInstance(int taskId) {
        Bundle args = new Bundle();
        args.putInt(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        int taskId = getArguments().getInt(ARG_TASK_ID,0);
        mTask = TaskLab.get(getActivity()).getTask(taskId);
    }


    @Override
    public void onPause() {
        super.onPause();
        TaskLab.get(getActivity())
                .updateTask(mTask);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_remove_task:
                TaskLab.get(getActivity()).removeTask(mTask);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        mTitleField = (EditText)v.findViewById(R.id.task_title);
        mTitleField.setText(mTask.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDescField = (EditText) v.findViewById(R.id.task_description);
        mDescField.setText(mTask.getDescription());
        mDescField.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                              }

                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                  mTask.setDescription(s.toString());
                                              }

                                              @Override
                                              public void afterTextChanged(Editable s) {

                                              }
                                          });
        mDueDateButton = (Button)v.findViewById(R.id.task_duedate);
        updateDate();
        mDueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mTask.getDueDate());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });

        mDueTimeButton = (Button)v.findViewById(R.id.task_duetime);
        updateTime();
        mDueTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mTask.getDueDate());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_TIME);
                dialog.show(manager,DIALOG_TIME);
            }
        });
        return v;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTask.setDueDate(date);
            updateDate();
        }

        if (requestCode == REQUEST_TIME) {
            Date date = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mTask.setDueDate(date);
            updateTime();
        }
    }

    private void updateDate() {
        mDueDateButton.setText(new SimpleDateFormat("MMM dd, yyyy").format(mTask.getDueDate()));
    }

    private void updateTime() {
        mDueTimeButton.setText(new SimpleDateFormat("hh:mm aa").format(mTask.getDueDate()));
    }
}
