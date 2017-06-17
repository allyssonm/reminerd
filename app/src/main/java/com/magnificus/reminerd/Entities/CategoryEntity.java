package com.magnificus.reminerd.Entities;

import android.graphics.Color;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity {

    private Long ID;
    private String Name;
    private Long IDColorEntity;
    private ColorEntity ColorEntity;

    public CategoryEntity(){}

    public CategoryEntity(long ID, String Name,Long IDColorEntity){
        this.ID = ID;
        this.Name = Name;
        this.IDColorEntity = IDColorEntity;
    }

    public CategoryEntity(long ID, String Name,Long IDColorEntity,ColorEntity ColorEntity){
        this.ID = ID;
        this.Name = Name;
        this.IDColorEntity = IDColorEntity;
        this.ColorEntity = ColorEntity;
    }


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getIDColorEntity() {
        return IDColorEntity;
    }

    public void setIDColorEntity(Long IDColorEntity) {
        this.IDColorEntity = IDColorEntity;
    }

    public ColorEntity getColorEntity() {
        return ColorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        ColorEntity = colorEntity;
    }
}
