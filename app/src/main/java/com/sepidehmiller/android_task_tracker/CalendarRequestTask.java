package com.sepidehmiller.android_task_tracker;

import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import java.io.IOException;

/**
 * Created by baghaii on 6/13/16.
 */


public class CalendarRequestTask extends AsyncTask<Task, Void, Boolean> {
    private com.google.api.services.calendar.Calendar mService = null;
    private Exception mLastError = null;

    public CalendarRequestTask(GoogleAccountCredential credential) {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("TaskTracker")
                .build();
    }

    /**
     * Background task to call Google Calendar API.
     * @tasks has the date, title, summary of the event.
     */
    @Override
    protected Boolean doInBackground(Task... tasks) {
        try {
            setEventInApi(tasks);
            return true;
        } catch (Exception e) {
            mLastError = e;
            cancel(true);
            return false;
        }
    }

    private void setEventInApi(Task... tasks) throws IOException {
        // Insert an event into the Google Calendar

        for (Task task: tasks) {
            Event event = new Event()
                    .setSummary(task.getTitle())
                    .setDescription(task.getDescription());
            DateTime startTime = new DateTime(task.getDueDate());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startTime);
            event.setStart(start);
            mService.events().insert("primary", event).execute();
        }
    }
}
