package com.magnificus.reminerd.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.TaskEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allysson on 31/05/17.
 */

public class TaskRepository extends SQLiteOpenHelper {

    private final Context context;

    public TaskRepository(Context context) {
        super(context, "Tasks", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Tasks (" +
                "ID INTEGER PRIMARY KEY, " +
                "Title TEXT NOT NULL, " +
                "Description TEXT NOT NULL, " +
                "Date DATE, " +
                "Hour TIME, " +
                "IDCategoryEntity INTEGER NOT NULL);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "QUERY AQUI";
                db.execSQL(sql);
        }*/
    }

    public List<TaskEntity> getTasks() {
        String sql = "SELECT * FROM Tasks";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<TaskEntity> tasks = populateTasks(c);

        return tasks;
    }

    @NonNull
    public List<TaskEntity> populateTasks(Cursor c) {
        List<TaskEntity> tasks = new ArrayList<TaskEntity>();

        while (c.moveToNext()) {
            TaskEntity task = new TaskEntity();
            task.setID(c.getLong(c.getColumnIndex("ID")));
            task.setTitle(c.getString(c.getColumnIndex("Title")));
            task.setDescription(c.getString(c.getColumnIndex("Description")));
            task.setDate(c.getString(c.getColumnIndex("Date")));
            task.setHour(c.getString(c.getColumnIndex("Hour")));
            task.setIDCategoryEntity(c.getLong(c.getColumnIndex("IDCategoryEntity")));

            tasks.add(task);
        }

        return tasks;
    }

    //TODO: This another gambex needs to be resolved later
    private CategoryEntity setCategoryObject(Long id) {
        CategoryRepository categoryRepository = new CategoryRepository(this.context);
        CategoryEntity categoryEntity = categoryRepository.getCategory(id);

        if (categoryEntity != null) {
            return categoryEntity;
        } else {
            categoryEntity.setID((long) 666);
            categoryEntity.setName("Default category");
            categoryEntity.setIDColorEntity((long) 1);
            categoryEntity.setColorEntity(categoryRepository.setColorObject((long) 1));

            return categoryEntity;
        }
    }

    public void insert(TaskEntity taskEntity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = buildTaskObject(taskEntity);
        db.insert("Tasks", null, data);
    }

    public void update(TaskEntity taskEntity) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = buildTaskObject(taskEntity);

        String[] params = {taskEntity.getID().toString()};
        db.update("Tasks", data, "ID = ?", params);
    }

    public void delete(TaskEntity taskEntity) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {taskEntity.getID().toString()};
        db.delete("Tasks", "id = ?", params);
    }

    @NonNull
    private ContentValues buildTaskObject(TaskEntity taskEntity) {
        ContentValues data = new ContentValues();
        data.put("ID", taskEntity.getID());
        data.put("Title", taskEntity.getTitle());
        data.put("Description", taskEntity.getDescription());
        data.put("Date", taskEntity.getDate());
        data.put("Hour", taskEntity.getHour());
        data.put("IDCategoryEntity", taskEntity.getIDCategoryEntity());

        return data;
    }
}
