package com.magnificus.reminerd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.magnificus.reminerd.Activities.CategoryFormActivity;
import com.magnificus.reminerd.Adapters.CategoriesAdapter;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.R;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Repositories.ColorRepository;
import com.magnificus.reminerd.Services.CategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gui on 28/05/17.
 */

public class CategoriesFragment extends Fragment {

    private ListView categoriasListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        categoriasListView = (ListView) view.findViewById(R.id.lista_categorias);

        categoriasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryEntity category = (CategoryEntity) categoriasListView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), CategoryFormActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
    }

    public void loadCategories() {
        CategoryRepository repository = new CategoryRepository(getContext());
        List<CategoryEntity> categories = repository.getCategories();
        repository.close();

        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), R.layout.row_category, categories);
        categoriasListView.setAdapter(adapter);
    }
}
