package com.magnificus.reminerd.Synchronizers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.magnificus.reminerd.Dto.ColorDto;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.Repositories.ColorRepository;
import com.magnificus.reminerd.Retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allysson on 19/06/17.
 */

public class ColorSync {
    private final Context context;

    public ColorSync(Context context) {
        this.context = context;
    }

    public void syncColors() {
        Call<ColorDto> call = new RetrofitInitializer().getColorService().list();
        call.enqueue(getColorsCallback());
    }

    public void insertColor(ColorEntity colorEntity) {
        if(!colorEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getColorService().insert(colorEntity);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("insertColor", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("insertColor", "onResponse: deu ruim" + t.getMessage());
                }
            });
        }
    }

    public void updateColor(ColorEntity colorEntity) {
        if(!colorEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getColorService().update(colorEntity.getID(), colorEntity);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("updateColor", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("updateColor", "onResponse: deu ruim" + t.getMessage());
                }
            });
        }
    }

    public void deleteColor(ColorEntity colorEntity) {
        if(!colorEntity.getID().isEmpty()) {
            Call call = new RetrofitInitializer().getColorService().delete(colorEntity.getID());

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("deleteColor", "onResponse: deu boa");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("deleteColor", "onFailure: deu ruim" + t.getMessage());
                }
            });
        }
    }

    @NonNull
    private Callback<ColorDto> getColorsCallback() {
        return new Callback<ColorDto>() {
            @Override
            public void onResponse(Call<ColorDto> call, Response<ColorDto> response) {
                ColorDto colorDto = response.body();

                ColorRepository repository = new ColorRepository(context);
                repository.syncColors(colorDto.getColors());
                repository.close();

                Log.i("syncColors", "onResponse: deu boa");
            }

            @Override
            public void onFailure(Call<ColorDto> call, Throwable t) {
                Log.i("syncColors", "onResponse: deu ruim " + t.getMessage());
            }
        };
    }



}
