package com.magnificus.reminerd.Entities;

/**
 * Created by allysson on 31/05/17.
 */

public class ColorEntity {

    private Long ID;
    private String Name;
    private String Hexadecimal;

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

    public String getHexadecimal() {
        return Hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        Hexadecimal = hexadecimal;
    }
}
