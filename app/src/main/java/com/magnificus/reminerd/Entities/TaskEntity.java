package com.magnificus.reminerd.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskEntity implements Serializable {

    private Long ID;
    private String Title;
    private String Description;
    private String Date;
    private java.sql.Date DateFormat;
    private String Time;
    private java.sql.Time TimeFormat;
    private Long IDCategoryEntity;
    private CategoryEntity CategoryEntity;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Long getIDCategoryEntity() {
        return IDCategoryEntity;
    }

    public void setIDCategoryEntity(Long IDCategoryEntity) {
        this.IDCategoryEntity = IDCategoryEntity;
    }

    public java.sql.Date getDateFormat() {
        return DateFormat;
    }

    public void setDateFormat(java.sql.Date dateFormat) {
        DateFormat = dateFormat;
    }

    public java.sql.Time getTimeFormat() {
        return TimeFormat;
    }

    public void setTimeFormat(java.sql.Time timeFormat) {
        TimeFormat = timeFormat;
    }

    public CategoryEntity getCategoryEntity() {
        return CategoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        CategoryEntity = categoryEntity;
    }
}
