package com.magnificus.reminerd.Entities;

import java.sql.Time;
import java.util.Date;

/**
 * Created by allysson on 31/05/17.
 */

public class TaskEntity {

    private Long ID;
    private String Title;
    private String Description;
    private String Date;
    private String Hour;
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

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public Long getIDCategoryEntity() {
        return IDCategoryEntity;
    }

    public void setIDCategoryEntity(Long IDCategoryEntity) {
        this.IDCategoryEntity = IDCategoryEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return CategoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        CategoryEntity = categoryEntity;
    }
}
