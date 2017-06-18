package com.magnificus.reminerd.Helpers;

import android.widget.EditText;
import android.widget.Spinner;

import com.magnificus.reminerd.Activities.CategoryFormActivity;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.R;

/**
 * Created by allysson on 17/06/17.
 */

public class CategoryFormHelper {

    private final EditText categoryName;
    private final Spinner categoryColor;

    private CategoryEntity categoryEntity;

    public CategoryFormHelper(CategoryFormActivity activity) {
        this.categoryEntity = new CategoryEntity();

        categoryName = (EditText) activity.findViewById(R.id.form_category_name);
        categoryColor = (Spinner) activity.findViewById(R.id.form_category_color);
    }

    public CategoryEntity getCategoryEntity() {
        ColorEntity color = (ColorEntity) categoryColor.getSelectedItem();

        categoryEntity.setName(categoryName.getText().toString());
        categoryEntity.setIDColorEntity(color.getID());
        categoryEntity.setColorEntity(color);

        return categoryEntity;
    }

    public void fillForm(CategoryEntity category) {
        this.categoryEntity = category;
        categoryName.setText(categoryEntity.getName());
    }

    public Boolean validateForm(){
        CategoryEntity entity = getCategoryEntity();

        if (entity.getIDColorEntity() == null
                || entity.getIDColorEntity() == 0
                || entity.getName() == null
                || entity.getName().isEmpty()) {
            return false;
        }

        return true;
    }

    public String getErrorMessage(){
        CategoryEntity entity = getCategoryEntity();
        if (entity.getIDColorEntity() == null || entity.getIDColorEntity() == 0)
            return "Selecione uma cor";
        if (entity.getName() == null || entity.getName().isEmpty())
            return "Digite um nome.";

        return "";
    }
}
