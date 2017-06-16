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
import com.magnificus.reminerd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gui on 28/05/17.
 */

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        ListView categoriasListView = (ListView) view.findViewById(R.id.lista_categorias);

        List<CategoryEntity> categories = new ArrayList<CategoryEntity>();

        categories.add(new CategoryEntity(1,"Teste Categoria 1"));
        categories.add(new CategoryEntity(1,"Teste Categoria 2"));
        categories.add(new CategoryEntity(1,"Teste Categoria 3"));
        categories.add(new CategoryEntity(1,"Teste Categoria 4"));
        categories.add(new CategoryEntity(1,"Teste Categoria 5"));
        categories.add(new CategoryEntity(1,"Teste Categoria 6"));
        categories.add(new CategoryEntity(1,"Teste Categoria 7"));
        categories.add(new CategoryEntity(1,"Teste Categoria 8"));
        categories.add(new CategoryEntity(1,"Teste Categoria 9"));
        categories.add(new CategoryEntity(1,"Teste Categoria 10"));
        categories.add(new CategoryEntity(1,"Teste Categoria 11"));

        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), R.layout.row_category, categories);

        categoriasListView.setAdapter(adapter);

        return view;
    }

}
