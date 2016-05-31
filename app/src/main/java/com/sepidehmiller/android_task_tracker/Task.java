package com.sepidehmiller.android_task_tracker;

import java.util.Date;

/**
 * Created by baghaii on 5/11/16.
 */
public class Task {
    private static int mInternalId = 0;
    private int mId;
    private String mTitle;
    private String mDescription;
    private Date mDueDate;
    private Date mCreateDate;
    private int mPriority;

    public Task() {
        mId = mInternalId;
        mInternalId = mInternalId + 1;
        mDueDate = new Date();
        mCreateDate = new Date();
        mPriority = 0; /* Should be scale of 1 to 5 */

    }

    public Task(int id) {
        mId = id;
        mDueDate = new Date();
        mCreateDate = new Date();
        mPriority = 0; /* Should be scale of 1 to 5 */
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() { return mTitle; }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setCreateDate(Date createDate) {
        mCreateDate = createDate;
    }

    public Date getCreateDate() {
        return mCreateDate;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public int getPriority() { return mPriority; }
}
