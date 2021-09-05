package com.cwaliimran.newsapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cwaliimran.newsapp.response.ArticleResponse;
import com.cwaliimran.newsapp.retrofit.ApiRequest;
import com.cwaliimran.newsapp.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public ArticleRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getDashboardNews() {

        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getTopHeadlines()
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
//                    if (response.isSuccessful()){
//                        Log.d(TAG, "onResponse: success");
//                    }
//                        Gson gson = new Gson().toJson(response.body());
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: " + t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }
}
