package com.cwaliimran.newsapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cwaliimran.newsapp.models.ModelArticles;
import com.cwaliimran.newsapp.retrofit.APIClient;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();

    public LiveData<ModelArticles> getDashboardNews() {
        final MutableLiveData<ModelArticles> data = new MutableLiveData<>();
        Call<ModelArticles> call = APIClient.getInstance().getApi().getTopHeadlines();
        Log.d(TAG, "URL==" + call.request().url());
        call.enqueue(new Callback<ModelArticles>() {
            @Override
            public void onResponse(@NonNull Call<ModelArticles> call, @NonNull Response<ModelArticles> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: success==" + new Gson().toJson(response.body()));
                        data.setValue(response.body());
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String errorMsg = jObjError.getString("message");
//                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
//                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelArticles> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                data.setValue(null);
            }
        });
        return data;
    }
}
