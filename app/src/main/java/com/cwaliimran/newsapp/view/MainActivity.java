package com.cwaliimran.newsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.cwaliimran.newsapp.adapters.ArticlesAdapter;
import com.cwaliimran.newsapp.databinding.ActivityMainBinding;
import com.cwaliimran.newsapp.models.ModelArticles;
import com.cwaliimran.newsapp.view_models.ArticlesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    ArticlesViewModel articlesViewModel;
    ArticlesAdapter adapter;
    LayoutInflater inflater;
    private ArrayList<ModelArticles.Article> articleArrayList = new ArrayList<>();

    //    ModelArticles modelArticles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        getArticles();
    }

    private void getArticles() {
        articlesViewModel.getArticleResponseLiveData().observe(this, modelArticles -> {
            if (modelArticles != null && modelArticles.getArticles() != null & !modelArticles.getArticles().isEmpty()) {
                binding.progressBar.setVisibility(View.GONE);
                articleArrayList.addAll(modelArticles.getArticles());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {
        adapter = new ArticlesAdapter(this, articleArrayList);
        binding.recyclerView.setAdapter(adapter);
        articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
    }
}