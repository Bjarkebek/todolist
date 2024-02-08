package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoItem> {

    private List<TodoItem> list;

    public TodoAdapter(Context context, List<TodoItem> items) {
        super(context, R.layout.view_todo, items);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_task, parent, false);


        TodoItem item = getItem(position);

        ((TextView) convertView.findViewById(R.id.task_Description)).setText(item.name);
        ((TextView) convertView.findViewById(R.id.task_CreateTime)).setText(item.createdAt.toString());
        ((TextView) convertView.findViewById(R.id.task_Deadline)).setText("Deadline " + item.deadline.toString());
        ((CheckBox) convertView.findViewById(R.id.task_checkbox)).setChecked(item.checkBox.isChecked());


        return convertView;
    }
}
