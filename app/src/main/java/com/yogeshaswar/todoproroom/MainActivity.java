package com.yogeshaswar.todoproroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static RecyclerView rvTask;
    static List<Task> list;
    static TaskAdapter taskAdapter;
    TaskAppDatabase db;
    static TaskDao taskDao;

    //variables
    static EditText edtTask;
    CheckBox isPriority;
    Button btnAddTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO -> 1. multiselect, 2. multiDelete 3. task complete check box checked -> card animation

        initiateUi();

        // recycler view setup
        rvTask.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Task>();
        db = TaskAppDatabase.getDB(MainActivity.this);
        taskDao = db.taskDao();
        getData();
        taskAdapter = new TaskAdapter(this, list, edtTask);
        rvTask.setAdapter(taskAdapter);

        // handle button click
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add task to db on add button click
                addToDatabase();
            }
        });
    }

    private void initiateUi() {
        rvTask = findViewById(R.id.rv_show_tasks);
        edtTask = findViewById(R.id.edt_task);
        isPriority = findViewById(R.id.cb_priority);
        btnAddTask = findViewById(R.id.btn_add);
    }

    private void getData() {
        // get data from db to load on screen/recycler view
        list = db.taskDao().getAllTask();
    }

    private void addToDatabase() {
        // check if empty
        if(edtTask.getText().toString().equals("")){
            Toast.makeText(this, "Task is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // create task object
        Task newTask = new Task(edtTask.getText().toString(), isPriority.isChecked());
        // add to db
        taskDao.addTask(newTask);
        // get updated task list from db
        list = taskDao.getAllTask();
        // pass updated task list to adapter
        taskAdapter = new TaskAdapter(MainActivity.this, list, edtTask);
        rvTask.setAdapter(taskAdapter);
        // erase added task from editTextField
        edtTask.setText("");

    }

    // delete
    public static void delete(Task task) {
        taskDao.deleteTask(task);
        list = taskDao.getAllTask();
        rvTask.setAdapter(new TaskAdapter(edtTask.getContext(), list, edtTask));
    }
}