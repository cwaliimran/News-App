package com.cwaliimran.newsapp.response;

import com.cwaliimran.newsapp.models.Article;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {

    @SerializedName("articles")
    @Expose
    public List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "BashboardNewsResponse{" +
                "articles=" + articles +
                '}';
    }
}
