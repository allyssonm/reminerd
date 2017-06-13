package com.magnificus.reminerd.Services;

import com.magnificus.reminerd.Dto.ColorSync;
import com.magnificus.reminerd.Entities.ColorEntity;

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

public interface ColorService {

    @GET("colors")
    Call<ColorSync> list();

    @POST("colors")
    Call<Void> insert(@Body ColorEntity colorEntity);

    @PUT("colors/{id}")
    Call<Void> update(@Path("id") String id, @Body ColorEntity colorEntity);

    @DELETE("colors/{id}")
    Call<Void> delete(@Path("id") String id);

}
