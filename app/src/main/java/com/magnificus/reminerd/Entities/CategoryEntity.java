package com.magnificus.reminerd.Entities;

/**
 * Created by allysson on 31/05/17.
 */

public class CategoryEntity {

    private Long ID;
    private String Name;
    private Long IDColorEntity;

    public CategoryEntity(){}

    public CategoryEntity(long ID, String Name){
        this.ID = ID;
        this.Name = Name;
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
}
