package com.ajs.simplemvvm.ui.blog;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ajs.simplemvvm.base.BaseViewModel;
import com.ajs.simplemvvm.model.OpenSourceResponse;
import com.ajs.simplemvvm.network.Api;
import com.ajs.simplemvvm.network.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogViewModel extends BaseViewModel {

    List<OpenSourceResponse.Repo> repos = new ArrayList<>();
    MutableLiveData<List<OpenSourceResponse.Repo>>reposLD = new MutableLiveData<>();

    public MutableLiveData<List<OpenSourceResponse.Repo>> fetchOpenSource() {
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("access_token", "demo.token.from.mock.server");
        headersMap.put("api_key", "ABCXYZ123TEST");
        headersMap.put("user_id", "1");
        Call<OpenSourceResponse> apiCaller = RetrofitService.createService(Api.class).getOpenSourceApiCall(headersMap);
        apiCaller.enqueue(new Callback<OpenSourceResponse>() {
            @Override
            public void onResponse(Call<OpenSourceResponse> call, Response<OpenSourceResponse> response) {
                if (response.isSuccessful()) {
                    repos = response.body().getData();
                    reposLD.setValue(response.body().getData());
                }
                Log.d("ANNV", "fetchOpenSource list repos  " + repos.size());
            }

            @Override
            public void onFailure(Call<OpenSourceResponse> call, Throwable t) {
                Log.d("ANNV", "list repos " + repos.size());
                reposLD.setValue(new ArrayList<>());
            }
        });
        return reposLD;
    }

}
