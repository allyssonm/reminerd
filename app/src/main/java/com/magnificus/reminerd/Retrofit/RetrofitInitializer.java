package com.magnificus.reminerd.Retrofit;

import com.magnificus.reminerd.Services.CategoryService;
import com.magnificus.reminerd.Services.ColorService;
import com.magnificus.reminerd.Services.TaskService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by allysson on 12/06/17.
 */

public class RetrofitInitializer {

    private final Retrofit retrofit;
    private final String baseUrl = "KOKOK";

    public RetrofitInitializer() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public TaskService getTaskService() {
        return retrofit.create(TaskService.class);
    }

    public CategoryService getCategoryService() {
        return retrofit.create(CategoryService.class);
    }

    public ColorService getColorService() {
        return retrofit.create(ColorService.class);
    }
}
