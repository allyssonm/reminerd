package com.magnificus.reminerd.Entities;

import android.graphics.Color;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity implements Serializable {

    private String ID;
    private String Name;
    private String IDColorEntity;
    private ColorEntity ColorEntity;

    public CategoryEntity(){}

    public CategoryEntity(String ID, String Name, String IDColorEntity){
        this.ID = ID;
        this.Name = Name;
        this.IDColorEntity = IDColorEntity;
    }

    public CategoryEntity(String ID, String Name, String IDColorEntity, ColorEntity ColorEntity){
        this.ID = ID;
        this.Name = Name;
        this.IDColorEntity = IDColorEntity;
        this.ColorEntity = ColorEntity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIDColorEntity() {
        return IDColorEntity;
    }

    public void setIDColorEntity(String IDColorEntity) {
        this.IDColorEntity = IDColorEntity;
    }

    public ColorEntity getColorEntity() {
        return ColorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        ColorEntity = colorEntity;
    }

    @Override
    public String toString() {
        return "Categoria: " + getName() + ", Cor: " + getColorEntity().getName();
    }
}
