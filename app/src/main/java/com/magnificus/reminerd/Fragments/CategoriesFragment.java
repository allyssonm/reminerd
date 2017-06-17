package com.magnificus.reminerd.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    CategoryRepository repository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        repository = new CategoryRepository(getContext());

        ListView categoriasListView = (ListView) view.findViewById(R.id.lista_categorias);

        List<CategoryEntity> categories = repository.getCategories();

        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), R.layout.row_category, categories);

        categoriasListView.setAdapter(adapter);

        return view;
    }

}
