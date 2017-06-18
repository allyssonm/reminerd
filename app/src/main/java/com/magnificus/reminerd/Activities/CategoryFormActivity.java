package com.magnificus.reminerd.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.magnificus.reminerd.Adapters.ColorSpinnerAdapter;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Helpers.CategoryFormHelper;
import com.magnificus.reminerd.R;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Repositories.ColorRepository;

import java.util.List;

public class CategoryFormActivity extends AppCompatActivity {

    private Spinner categoryColor;
    private CategoryFormHelper helper;
    private CategoryEntity categoryEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_form);

        categoryColor = (Spinner) findViewById(R.id.form_category_color);

        helper = new CategoryFormHelper(this);

        Intent intent = getIntent();
        categoryEntity = (CategoryEntity) intent.getSerializableExtra("category");

        if(categoryEntity != null) {
            helper.fillForm(categoryEntity);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSpinner();
    }

    private void loadSpinner() {
        ColorRepository repository = new ColorRepository(this);
        List<ColorEntity> colorEntityList = repository.getColors();
        repository.close();

        ColorSpinnerAdapter adapter = new ColorSpinnerAdapter(this, colorEntityList);
        categoryColor.setAdapter(adapter);
        if(categoryEntity != null && categoryEntity.getColorEntity().getHexadecimal() != null) {
            int position = getIndex(categoryColor, categoryEntity.getColorEntity());
            categoryColor.setSelection(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_confirm:
                CategoryEntity category = helper.getCategoryEntity();

                CategoryRepository repository = new CategoryRepository(this);

                if(category.getID() != null) {
                    repository.update(category);
                } else {
                    repository.insert(category);
                }

                repository.close();

                //TODO: call retrofit vem depois desse processo, tirar essa responsabilidade de inserir e ws daqui

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Found at world wide web, adapted for us
     * @param spinner your spinner dude
     * @param colorEntity your color entity dude
     * @return index of spinner dude
     */
    private int getIndex(Spinner spinner, ColorEntity colorEntity)
    {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++){
            ColorEntity color = (ColorEntity) spinner.getItemAtPosition(i);

            if (color.getName().equals(colorEntity.getName())){
                index = i;
                break;
            }
        }
        return index;
    }
}
