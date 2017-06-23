package com.magnificus.reminerd.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.magnificus.reminerd.Activities.CategoryFormActivity;
import com.magnificus.reminerd.Adapters.CategoriesAdapter;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.Events.UpdateCategoriesListEvent;
import com.magnificus.reminerd.R;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Repositories.ColorRepository;
import com.magnificus.reminerd.Repositories.TaskRepository;
import com.magnificus.reminerd.Services.CategoryService;
import com.magnificus.reminerd.Synchronizers.CategorySync;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gui on 28/05/17.
 */

public class CategoriesFragment extends Fragment {

    private ListView categoriasListView;
    private final CategorySync categorySync = new CategorySync(getContext());
    private SwipeRefreshLayout swipe;
    private EventBus eventBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        eventBus = EventBus.getDefault();

        categoriasListView = (ListView) view.findViewById(R.id.lista_categorias);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_categories_list);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CategorySync syncCategory = new CategorySync(getContext());
                syncCategory.syncCategories();
            }
        });

        categoriasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryEntity category = (CategoryEntity) categoriasListView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), CategoryFormActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        registerForContextMenu(categoriasListView);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCategoriesListEvent(UpdateCategoriesListEvent event){
        if(swipe.isRefreshing())
            swipe.setRefreshing(false);
        loadCategories();
    }

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
        loadCategories();
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    public void loadCategories() {
        CategoryRepository repository = new CategoryRepository(getContext());
        List<CategoryEntity> categories = repository.getCategories();
        repository.close();

        for (CategoryEntity categoryEntity : categories) {
            Log.i("loadCateg", "loadCategories: " + categoryEntity.getName() + " " + categoryEntity.getUpdated());
        }

        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), R.layout.row_category, categories);
        categoriasListView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final CategoryEntity category = (CategoryEntity) categoriasListView.getItemAtPosition(info.position);

        MenuItem itemDelete = menu.add("Excluir");
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                TaskRepository taskRepository = new TaskRepository(getContext());
                List<TaskEntity> taskEntityList = taskRepository.getTasksByCategory(category.getID());
                taskRepository.close();

                if (taskEntityList.size() > 0) {
                    showToast(getContext(), "Não é possível excluir, existem tasks associadas com essa categoria");
                } else {
                    showToast(getContext(), "Categoria: " + category.getName() + " excluída.");
                    CategoryRepository categoryRepository = new CategoryRepository(getContext());
                    categoryRepository.delete(category);
                    categoryRepository.close();
                    categorySync.deleteCategory(category);

                    loadCategories();
                }

                return false;
            }
        });
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
