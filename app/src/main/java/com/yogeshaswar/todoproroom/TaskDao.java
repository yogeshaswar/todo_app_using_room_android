package com.yogeshaswar.todoproroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    //methods
    @Insert
    public void addTask(Task task);
    @Update
    public void updateTask(Task task);
    @Delete
    public void deleteTask(Task task);
    // get all tasks list
    @Query(value = " SELECT * FROM task_table ")
    List<Task> getAllTask();
}
