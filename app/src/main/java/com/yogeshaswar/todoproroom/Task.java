package com.yogeshaswar.todoproroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "task")
    public String task;
    @ColumnInfo(name = "priority")
    public boolean isPriority;

    public Task(int id, String task, boolean isPriority) {
        this.id = id;
        this.task = task;
        this.isPriority = isPriority;
    }

    //constructor
    @Ignore
    public Task(String task, boolean isPriority) {
//        this.id = id;
        this.task = task;
        this.isPriority = isPriority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }
}
