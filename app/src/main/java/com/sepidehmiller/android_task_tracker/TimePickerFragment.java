package com.sepidehmiller.android_task_tracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by baghaii on 5/19/16.
 */
public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_TIME = "com.sepidehmiller.android_task_tracker.time";
    public static final String ARG_TIME = "time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);


        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        if (Build.VERSION.SDK_INT  < 23) {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        } else {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                int minute = 0;
                                if (Build.VERSION.SDK_INT  < 23) {
                                    hour = mTimePicker.getCurrentHour();
                                    minute = mTimePicker.getCurrentMinute();
                                } else {
                                    hour = mTimePicker.getHour();
                                    minute = mTimePicker.getMinute();
                                }
                                Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
