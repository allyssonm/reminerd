package com.magnificus.reminerd.Helpers;

import android.widget.EditText;
import android.widget.Spinner;

import com.magnificus.reminerd.Activities.TaskFormActivity;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by allysson on 17/06/17.
 */

public class TaskFormHelper {

    private final EditText taskTitle;
    private final EditText taskDescription;
    private final EditText taskDate;
    private final EditText taskTime;
    private final Spinner taskCategory;

    private TaskEntity taskEntity;
    private String dateToFormat;
    private String timeToFormat;

    public TaskFormHelper(TaskFormActivity activity) {
        this.taskEntity = new TaskEntity();

        taskTitle = (EditText) activity.findViewById(R.id.form_task_title);
        taskTime = (EditText) activity.findViewById(R.id.form_task_time);
        taskDescription = (EditText) activity.findViewById(R.id.form_task_description);
        taskDate = (EditText) activity.findViewById(R.id.form_task_date);
        taskCategory = (Spinner) activity.findViewById(R.id.form_task_category);

        dateToFormat = activity.getDateSelected();
        timeToFormat = activity.getTimeSelected();
    }

    public TaskEntity getTaskEntity(){
        CategoryEntity category = (CategoryEntity) taskCategory.getSelectedItem();

        taskEntity.setTitle(taskTitle.getText().toString());
        taskEntity.setDescription(taskDescription.getText().toString());
        taskEntity.setTime(taskTime.getText().toString());
        taskEntity.setDate(taskDate.getText().toString());
        taskEntity.setIDCategoryEntity(category.getID());
        taskEntity.setCategoryEntity(category);

        return taskEntity;
    }

    public void fillForm(TaskEntity task) {
        this.taskEntity = task;

        taskTitle.setText(taskEntity.getTitle());
        taskDescription.setText(taskEntity.getDescription());
        taskTime.setText(taskEntity.getTime());
        taskDate.setText(taskEntity.getDate());
    }

    private Date stringToDate(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (Date) simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

}
