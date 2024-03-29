package com.ajs.simplemvvm.ui.heroes;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ajs.simplemvvm.base.BaseViewModel;
import com.ajs.simplemvvm.model.Hero;
import com.ajs.simplemvvm.network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesViewModel extends BaseViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Hero>> heroList;

    public MutableLiveData<List<Hero>> getHeroes() {
        if(heroList == null) {
            heroList = new MutableLiveData<>();
            loadHeroes();
        }

        return heroList;
    }

    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.HERO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("ANNV","list repos " );
            }
        });

    }
}
