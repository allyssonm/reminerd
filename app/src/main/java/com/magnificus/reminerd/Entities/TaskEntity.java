package com.magnificus.reminerd.Entities;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskEntity implements Serializable {

    private String ID;
    private String Title;
    private String Description;
    private String Date;
    private String Time;
    private String IDCategoryEntity;
    private CategoryEntity CategoryEntity;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    //Database
    public void setDate(String date) {
        try {
            java.util.Date dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            this.Date = new SimpleDateFormat("yyyy-MM-dd").format(dateUtil);
        } catch (ParseException e) {
            Log.e("setDate", e.getMessage());
        }
    }

    //Database
    public String getDate() {
        return Date;
    }

    //FormActivity
    public void setFormattedDate(String date) {
        try {
            java.util.Date dateUtil = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            this.Date = new SimpleDateFormat("yyyy-MM-dd").format(dateUtil);
        } catch (ParseException e) {
            Log.e("setDate", e.getMessage());
        }
    }

    //FormActivity
    public String getFormattedDate() {
        try {
            java.util.Date dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(this.Date);
            return new SimpleDateFormat("dd/MM/yyyy").format(dateUtil);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getIDCategoryEntity() {
        return IDCategoryEntity;
    }

    public void setIDCategoryEntity(String IDCategoryEntity) {
        this.IDCategoryEntity = IDCategoryEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return CategoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        CategoryEntity = categoryEntity;
    }
}
