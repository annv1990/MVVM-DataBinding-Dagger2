package com.ajs.simplemvvm.ui.opensource;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ajs.simplemvvm.model.OpenSourceResponse;
import com.ajs.simplemvvm.network.Api;
import com.ajs.simplemvvm.network.RetrofitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenSourceViewModel {

    private final MutableLiveData<List<OpenSourceItemModel>> openSourceItemsLiveDate;

    public OpenSourceViewModel() {
        openSourceItemsLiveDate = new MutableLiveData<>();

    }

    public void fetchRepository() {
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("access_token", "demo.token.from.mock.server");
        headersMap.put("api_key", "ABCXYZ123TEST");
        headersMap.put("user_id", "1");
        Call<OpenSourceResponse> apiCaller = RetrofitService.createService(Api.class).getOpenSourceApiCall(headersMap);
        apiCaller.enqueue(new Callback<OpenSourceResponse>() {
            @Override
            public void onResponse(Call<OpenSourceResponse> call, Response<OpenSourceResponse> response) {
                if (response.isSuccessful()) {
//                    openSourceItemsLiveDate.setValue(response.body().getData());
                }

                Log.d("ANNV", "list repos ");
            }

            @Override
            public void onFailure(Call<OpenSourceResponse> call, Throwable t) {
                Log.d("ANNV", "list repos ");
            }
        });

    }



}
