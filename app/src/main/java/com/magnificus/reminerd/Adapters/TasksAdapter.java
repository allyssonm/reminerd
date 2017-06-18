package com.magnificus.reminerd.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.R;

import java.util.List;

/**
 * Created by allysson on 18/06/17.
 */

public class TasksAdapter extends ArrayAdapter<TaskEntity> {

    public TasksAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TasksAdapter(Context context, int resource, List<TaskEntity> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(R.layout.row_task, null);
        }

        TaskEntity task = getItem(position);

        if (task != null) {

            View color_display = (View) view.findViewById(R.id.row_task_color);
            TextView tv_title = (TextView) view.findViewById(R.id.row_task_title);

            ColorEntity categoryColor = task.getCategoryEntity().getColorEntity();

            if (color_display != null && categoryColor != null) {
                GradientDrawable bg = (GradientDrawable) color_display.getBackground();
                bg.setColor(Color.parseColor(categoryColor.getHexadecimal()));
            }

            if (tv_title != null)
                tv_title.setText(task.getTitle());

        }

        return view;
    }
}
