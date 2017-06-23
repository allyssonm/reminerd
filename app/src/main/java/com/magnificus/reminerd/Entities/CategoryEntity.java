package com.magnificus.reminerd.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity implements Serializable {

    private String ID;
    private String Name;
    @JsonProperty("color_id")
    private String IDColorEntity;
    private ColorEntity ColorEntity;
    @JsonIgnoreProperties
    private int Updated;
    @JsonIgnoreProperties
    private int Deleted;

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

    public int getUpdated() {
        return Updated;
    }

    public void setUpdated(int updated) {
        this.Updated = updated;
    }

    public void updated() {
        this.Updated = 1;
    }

    public void outdate() {
        this.Updated = 0;
    }

    public int getDeleted() {
        return Deleted;
    }

    public void setDeleted(int deleted) {
        this.Deleted = deleted;
    }

    @Override
    public String toString() {
        return "Categoria: " + getName();
    }
}
