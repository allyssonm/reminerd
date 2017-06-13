package com.magnificus.reminerd.Services;

import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.Dto.TaskSync;

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

public interface TaskService {

    @GET("tasks")
    Call<TaskSync> list();

    @POST("tasks")
    Call<Void> insert(@Body TaskEntity taskEntity);

    @PUT("tasks/{id}")
    Call<Void> update(@Path("id") String id, @Body TaskEntity taskEntity);

    @DELETE("colors/{id}")
    Call<Void> delete(@Path("id") String id);
}
