package com.cwaliimran.newsapp.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cwaliimran.newsapp.models.ModelArticles;
import com.cwaliimran.newsapp.repository.ArticleRepository;

public class ArticlesViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;

    private LiveData<ModelArticles> articleResponseLiveData;


    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getDashboardNews();
    }

    public LiveData<ModelArticles> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
