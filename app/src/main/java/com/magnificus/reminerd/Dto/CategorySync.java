package com.magnificus.reminerd.Dto;

import com.magnificus.reminerd.Entities.CategoryEntity;

import java.util.List;

/**
 * Created by allysson on 12/06/17.
 */

public class CategorySync {

    private List<CategoryEntity> categories;

    public List<CategoryEntity> getCategories(){
        return this.categories;
    }

}
