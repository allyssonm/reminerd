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

    public void fillForm(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
        categoryName.setText(categoryEntity.getName());
    }
}
