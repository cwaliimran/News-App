package com.cwaliimran.newsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.cwaliimran.newsapp.adapters.ArticlesAdapter;
import com.cwaliimran.newsapp.databinding.ActivityMainBinding;
import com.cwaliimran.newsapp.models.Article;
import com.cwaliimran.newsapp.view_models.ArticlesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    ArticlesViewModel articlesViewModel;
    ArticlesAdapter adapter;
    LayoutInflater inflater;
    private ArrayList<Article> articleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        getArticles();
    }

    private void initViews() {
        adapter = new ArticlesAdapter(this, articleArrayList);
        binding.recyclerView.setAdapter(adapter);
        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
    }

    private void getArticles() {
        articlesViewModel.getBashboardNewsResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null && articleResponse.getArticles() != null && !articleResponse.getArticles().isEmpty()) {
                binding.progressBar.setVisibility(View.GONE);
                List<Article> articleList = articleResponse.getArticles();
                articleArrayList.addAll(articleList);
                adapter.notifyDataSetChanged();
            }
        });
    }
}