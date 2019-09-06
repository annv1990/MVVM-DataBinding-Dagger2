package com.ajs.simplemvvm.ui.opensource.fragment;

import android.arch.lifecycle.LiveData;
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

public class OpenSourceFragmentViewModel extends BaseViewModel {

    private final MutableLiveData<List<OpenSourceResponse.Repo>> reposLiveData;

    private final MutableLiveData<List<OpenSourceItemViewModel>> openSourceLiveData = new MutableLiveData<>();

    public OpenSourceFragmentViewModel() {
        reposLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<OpenSourceResponse.Repo>> fetchRepository() {
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("access_token", "demo.token.from.mock.server");
        headersMap.put("api_key", "ABCXYZ123TEST");
        headersMap.put("user_id", "1");
        Call<OpenSourceResponse> apiCaller = RetrofitService.createService(Api.class).getOpenSourceApiCall(headersMap);
        apiCaller.enqueue(new Callback<OpenSourceResponse>() {
            @Override
            public void onResponse(Call<OpenSourceResponse> call, Response<OpenSourceResponse> response) {
                if (response.isSuccessful()) {
                    reposLiveData.setValue(response.body().getData());
                    List<OpenSourceItemViewModel> viewModelList = new ArrayList<>();

                    for(OpenSourceResponse.Repo repo : response.body().getData()){
                        viewModelList.add(new OpenSourceItemViewModel(repo.getCoverImgUrl(), repo.getTitle(),
                                repo.getDescription(), repo.getProjectUrl()));
                    }
                    openSourceLiveData.setValue(viewModelList);
                }

                Log.d("ANNV", "list repos ");
            }

            @Override
            public void onFailure(Call<OpenSourceResponse> call, Throwable t) {
                Log.d("ANNV", "list repos ");
            }
        });
        return reposLiveData;
    }

    public LiveData<List<OpenSourceItemViewModel>> getOpenSourceListLiveData(){
        return openSourceLiveData;
    }


}
