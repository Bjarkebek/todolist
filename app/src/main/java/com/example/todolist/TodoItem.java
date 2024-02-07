package com.example.todolist;

import android.widget.CheckBox;
import android.widget.DatePicker;

import java.time.LocalDateTime;
import java.util.Date;

public class TodoItem {
    String name;
    LocalDateTime createdAt;
    LocalDateTime deadline;
    CheckBox checkBox;
}
