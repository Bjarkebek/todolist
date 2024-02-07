package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<TodoItem> todoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn_create = findViewById(R.id.btn_create_task);
        todoItemList = new ArrayList<>();
        MockData();


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskCreateActivity.class);
                startActivity(intent);
            }
        });

    }

    void MockData() {
        TodoItem t1 = new TodoItem();
        t1.name = "gør noget";
        t1.createdAt = LocalDateTime.now();
        t1.deadline = LocalDateTime.parse("2024-03-06T15:00");
        todoItemList.add(t1);

        TodoItem t2 = new TodoItem();
        t2.name = "gør noget mere";
        t2.createdAt = LocalDateTime.now();
        t2.deadline = LocalDateTime.parse("2024-03-08T12:00");
        todoItemList.add(t2);
        showItems();
    }

    void showItems() {
        ArrayAdapter<TodoItem> adapter = new TodoAdapter(this, todoItemList);
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);
    }

    void load(){
        String json = getSharedPreferences("TodoList", Context.MODE_PRIVATE)
                .getString("TodoList", null);
        if(json != null){
            todoItemList = new Gson().fromJson(json, new TypeToken<List<TodoItem>>(){
            }.getType());
        }
    }

    void save(){
        getSharedPreferences("TodoList", Context.MODE_PRIVATE)
                .getString("TodoList", null);
    }

}