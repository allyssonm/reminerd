package com.magnificus.reminerd.Synchronizers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.magnificus.reminerd.Dto.CategoryDto;
import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Events.UpdateCategoriesListEvent;
import com.magnificus.reminerd.Events.UpdateColorListEvent;
import com.magnificus.reminerd.Repositories.CategoryRepository;
import com.magnificus.reminerd.Retrofit.RetrofitInitializer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allysson on 20/06/17.
 */

public class CategorySync {
    private final Context context;
    private EventBus eventBus = EventBus.getDefault();

    public CategorySync(Context context) {
        this.context = context;
    }

    public void syncCategories() {
        Call<CategoryDto> call = new RetrofitInitializer().getCategoryService().list();
        call.enqueue(getCategoriesCallback());
    }

    public void insertCategory(CategoryEntity categoryEntity) {
        if(!categoryEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getCategoryService().insert(categoryEntity);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("insertCategory", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("insertCategory", "onFailure: deu ruim " + t.getMessage());
                }
            });
        }
    }

    public void updateCategory(final CategoryEntity categoryEntity) {
        if(!categoryEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getCategoryService().update(categoryEntity.getID(), categoryEntity);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if(response.body() == null) {
                        categoryEntity.updated();
                        new CategorySync(context).insertCategory(categoryEntity);
                    } else {
                        CategoryRepository repository = new CategoryRepository(context);
                        categoryEntity.updated();
                        repository.update(categoryEntity);
                        repository.close();
                        Log.i("updateCategory", "onResponse: deu boa");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("updateCategory", "onFailure: deu ruim " + t.getMessage());
                }
            });
        }
    }

    public void deleteCategory(CategoryEntity categoryEntity) {
        if(!categoryEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getCategoryService().delete(categoryEntity.getID());

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("deleteCategory", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("deleteCategory", "onFailure: deu ruim " + t.getMessage());
                }
            });
        }
    }

    public void syncCategoriesUpdated() {
        CategoryRepository repository = new CategoryRepository(context);
        List<CategoryEntity> categoryEntityList = repository.updatedList();
        repository.close();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            Log.i("categSync", "NOTSERVER: " + categoryEntity.getName() + " " + categoryEntity.getUpdated());
            this.updateCategory(categoryEntity);
        }
    }

    @NonNull
    private Callback<CategoryDto> getCategoriesCallback() {
        return new Callback<CategoryDto>() {
            @Override
            public void onResponse(Call<CategoryDto> call, Response<CategoryDto> response) {
                CategoryDto categoryDto = response.body();

                CategoryRepository repository = new CategoryRepository(context);
                repository.syncCategories(categoryDto.getCategories());
                repository.close();

                Log.i("syncCategories", "onResponse: deu boa");

                eventBus.post(new UpdateCategoriesListEvent());
            }

            @Override
            public void onFailure(Call<CategoryDto> call, Throwable t) {
                Log.i("syncCategories", "onFailure: deu ruim " + t.getMessage());
                eventBus.post(new UpdateCategoriesListEvent());
            }
        };
    }
}
