package com.magnificus.reminerd.Services;

import com.magnificus.reminerd.Dto.CategoryDto;
import com.magnificus.reminerd.Entities.CategoryEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by allysson on 12/06/17.
 */

public interface CategoryService {

    @GET("categories")
    Call<CategoryDto> list();

    @POST("categories")
    Call<Void> insert(@Body CategoryEntity categoryEntity);

    @PUT("categories/{id}")
    Call<Void> update(@Path("id") String id, @Body CategoryEntity categoryEntity);

    @DELETE("categories/{id}")
    Call<Void> delete(@Path("id") String id);
}
