package com.magnificus.reminerd.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.magnificus.reminerd.Adapters.CategorySpinnerAdapter;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.Helpers.TaskFormHelper;
import com.magnificus.reminerd.R;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Repositories.TaskRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskFormActivity extends AppCompatActivity {

    private Spinner taskCategory;
    private TaskFormHelper helper;
    private TaskEntity taskEntity;
    private EditText taskDate;
    private EditText taskTime;
    private String dateSelected;
    private String timeSelected;

    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int currentHour;
    private int currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        taskCategory = (Spinner) findViewById(R.id.form_task_category);

        helper = new TaskFormHelper(this);

        taskDate = (EditText) findViewById(R.id.form_task_date);
        taskTime = (EditText) findViewById(R.id.form_task_time);

        setCalendar();

        Intent intent = getIntent();
        taskEntity = (TaskEntity) intent.getSerializableExtra("task");

        if(taskEntity != null) {
            helper.fillForm(taskEntity);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSpinner();
    }

    private void loadSpinner() {
        CategoryRepository repository = new CategoryRepository(this);
        List<CategoryEntity> categoryEntityList = repository.getCategories();
        repository.close();

        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this, categoryEntityList);
        taskCategory.setAdapter(adapter);
        if(taskEntity != null && taskEntity.getCategoryEntity() != null) {
            int position = getIndex(taskCategory, taskEntity.getCategoryEntity());
            taskCategory.setSelection(position);
        }
    }

    private void setCalendar() {
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        dateSelected = String.valueOf(currentDay) + "/" + String.valueOf(currentMonth + 1) + "/" + String.valueOf(currentYear);
        timeSelected = String.valueOf(currentHour) + ":" + String.valueOf(currentMinute);
        taskDate.setText(dateSelected);
        taskTime.setText(timeSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_confirm:
                TaskEntity task = helper.getTaskEntity();

                TaskRepository repository = new TaskRepository(this);

                if(task.getID() != null) {
                    repository.update(task);
                } else {
                    repository.insert(task);
                }

                repository.close();

                //TODO: call retrofit vem depois desse processo, tirar essa responsabilidade de inserir e ws daqui

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pickDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(TaskFormActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {
                dateSelected = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(day);
                taskDate.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
            }
        }, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    public void pickTime(View view) {
        final TimePickerDialog timePickerDialog = new TimePickerDialog(TaskFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeSelected = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                taskTime.setText(timeSelected);
            }
        }, currentHour, currentMinute, true);
        timePickerDialog.show();
    }

    public String getDateSelected() {
        return this.dateSelected;
    }

    public String getTimeSelected() {
        return this.timeSelected;
    }

    /**
     * Found at world wide web, adapted for us
     * @param spinner your spinner dude
     * @param categoryEntity your category entity dude
     * @return index of spinner dude
     */
    private int getIndex(Spinner spinner, CategoryEntity categoryEntity)
    {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++){
            CategoryEntity category = (CategoryEntity) spinner.getItemAtPosition(i);

            if (category.getName().equals(categoryEntity.getName())){
                index = i;
                break;
            }
        }
        return index;
    }
}
