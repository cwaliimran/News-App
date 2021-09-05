package com.cwaliimran.newsapp.retrofit;

import com.cwaliimran.newsapp.models.ModelArticles;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.cwaliimran.newsapp.constants.AppConstants.API_KEY;

public interface ApiInterface {
    @GET("top-headlines?country=us&apiKey=" + API_KEY)
    Call<ModelArticles> getTopHeadlines();
}
