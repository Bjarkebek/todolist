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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<TodoItem> todoItemList;
    ListView listView = findViewById(R.id.listView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn_create = findViewById(R.id.btn_create_task);
        todoItemList = new ArrayList<>();
        MockData();


        //reads from internal storage and stores the file in a string
        String content = readFromFile("file.txt");
        Toast.makeText(this, content.getBytes().toString(), Toast.LENGTH_SHORT).show();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskCreateActivity.class);
                startActivity(intent);
            }
        });

    }

    private String readFromFile(String fileName) {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path,fileName);
        byte[] content = new byte[(int) readFrom.length()];


        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
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