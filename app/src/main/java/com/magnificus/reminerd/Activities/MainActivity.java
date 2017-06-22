package com.magnificus.reminerd.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Fragments.CategoriesFragment;
import com.magnificus.reminerd.Fragments.TasksFragment;
import com.magnificus.reminerd.R;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Repositories.ColorRepository;
import com.magnificus.reminerd.Synchronizers.CategorySync;
import com.magnificus.reminerd.Synchronizers.ColorSync;
import com.magnificus.reminerd.Synchronizers.TaskSync;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final ColorSync colorSync = new ColorSync(this);
    private final CategorySync categorySync = new CategorySync(this);
    private final TaskSync taskSync = new TaskSync(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeFragment(R.id.content, new TasksFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        colorSync.syncColors();
        categorySync.syncCategories();
        taskSync.syncTasks();

        showIdsCategories();
    }

    public void showIdsCategories() {
        CategoryRepository repository = new CategoryRepository(this);
        List<CategoryEntity> categoryEntityList = repository.getCategories();
        repository.close();

        for(CategoryEntity categoryEntity : categoryEntityList) {
            Log.i("showIdsCategories", "showIdsCategories: " + categoryEntity.getID());
        }
    }

    private void ChangeFragment(@IdRes int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(containerViewId, fragment);

        tx.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_task:
                startActivity(new Intent(this, TaskFormActivity.class));
                break;
            case R.id.menu_form_category:
                startActivity(new Intent(this, CategoryFormActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tasks:
                    ChangeFragment(R.id.content, new TasksFragment());
                    return true;
                case R.id.navigation_categorias:
                    ChangeFragment(R.id.content, new CategoriesFragment());
                    return true;
            }
            return false;
        }

    };
}
