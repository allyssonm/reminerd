package com.magnificus.reminerd.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by allysson on 31/05/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorEntity implements Serializable {

    private String ID;
    private String Name;
    private String Hexadecimal;

    public ColorEntity(){}

    public ColorEntity(String ID, String Name, String Hexadecimal){
        this.ID = ID;
        this.Name = Name;
        this.Hexadecimal = Hexadecimal;
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

    public String getHexadecimal() {
        return Hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        Hexadecimal = hexadecimal;
    }
}
