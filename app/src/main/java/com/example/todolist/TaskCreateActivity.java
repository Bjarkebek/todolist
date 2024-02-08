package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TaskCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        EditText name = findViewById(R.id.editText_TaskName);
        Button btn_add = findViewById(R.id.btn_add);

        TimePicker deadline_time = findViewById(R.id.dp_deadline_time);
        deadline_time.setIs24HourView(true);

        DatePicker deadline_date = findViewById(R.id.dp_deadline_date);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().length() > 0) {
                    TodoItem task = new TodoItem();

                    task.name = name.getText().toString();
                    task.createdAt = LocalDateTime.now();
                    task.deadline = LocalDateTime.of(
                            deadline_date.getYear(),
                            deadline_date.getMonth(),
                            deadline_date.getDayOfMonth(),
                            deadline_time.getHour(),
                            deadline_time.getMinute()
                    );

                    // add task to internal storage
                    writeToFile("file.txt",task.name.toString());


                    // routes back to main page
                    Intent intent = new Intent(TaskCreateActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void writeToFile(String fileName, String content){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}