package com.magnificus.reminerd.Synchronizers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.magnificus.reminerd.Dto.TaskDto;
import com.magnificus.reminerd.Entities.TaskEntity;
import com.magnificus.reminerd.Repositories.TaskRepository;
import com.magnificus.reminerd.Retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allysson on 20/06/17.
 */

public class TaskSync {
    private final Context context;

    public TaskSync(Context context) {
        this.context = context;
    }

    public void syncTasks() {
        Call<TaskDto> call = new RetrofitInitializer().getTaskService().list();
        call.enqueue(getTasksCallback());
    }

    public void insertTask(TaskEntity taskEntity) {
        if(!taskEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getTaskService().insert(taskEntity);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("insertTask", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("insertTask", "onFailure: deu ruim " + t.getMessage());
                }
            });
        }
    }

    public void updateTask(TaskEntity taskEntity) {
        Call call = new RetrofitInitializer().getTaskService().update(taskEntity.getID(), taskEntity);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("updateTask", "onResponse: deu boa");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("updateTask", "onFailure: deu ruim " + t.getMessage());
            }
        });
    }

    public void deleteTask(TaskEntity taskEntity) {
        Call call = new RetrofitInitializer().getTaskService().delete(taskEntity.getID());

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("deleteTask", "onResponse: deu boa");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("deleteTask", "onFailure: deu ruim " + t.getMessage());
            }
        });
    }

    @NonNull
    private Callback<TaskDto> getTasksCallback() {
        return new Callback<TaskDto>() {
            @Override
            public void onResponse(Call<TaskDto> call, Response<TaskDto> response) {
                TaskDto taskDto = (TaskDto) response.body();

                TaskRepository repository = new TaskRepository(context);
                repository.syncTasks(taskDto.getTasks());
                repository.close();

                Log.i("syncTasks", "onResponse: deu boa");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("syncTasks", "onFailure: deu ruim " + t.getMessage());
            }
        };
    }
}
