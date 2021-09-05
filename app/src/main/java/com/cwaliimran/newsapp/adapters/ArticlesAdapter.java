package com.cwaliimran.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cwaliimran.newsapp.databinding.RowNewsBinding;
import com.cwaliimran.newsapp.models.ModelArticles;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    Context context;
    ArrayList<ModelArticles.Article> articleArrayList;
    LayoutInflater inflater;

    public ArticlesAdapter(@NonNull Context context, ArrayList<ModelArticles.Article> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowNewsBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, int position) {
        ModelArticles.Article article = articleArrayList.get(position);
        holder.binding.tvTitle.setText(article.getTitle());
        Glide.with(context).load(article.getUrlToImage()).into(holder.binding.ivNews);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowNewsBinding binding;

        public ViewHolder(@NonNull RowNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
