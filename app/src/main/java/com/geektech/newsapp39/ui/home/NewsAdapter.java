package com.geektech.newsapp39.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.ItemNewsBinding;
import com.geektech.newsapp39.models.News;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("NotifyDataSetChanged")
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private ItemNewsBinding binding;
    private onItemClickListener onItemClickListener;

    public void addNewsItem(News news) {
        newsList.add(news);
        notifyItemInserted(newsList.size());
    }

    public void deleteNews(int pos) {
        this.newsList.remove(pos);
        notifyItemRemoved(pos);
    }



    public News getNews(int pos) {
        return newsList.get(pos);
    }

    public void addItems(List<News> list) {
        this.newsList = list;
        notifyDataSetChanged();
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;

    }

    public void setOnItemClickListener(NewsAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());


            itemView.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(getAdapterPosition());
            });

            itemView.getRoot().setOnLongClickListener(v -> {
                onItemClickListener.onLongItemClick(getAdapterPosition());
                return true;
            });
        }

        public void onBind(News news) {
            binding.titleTv.setText(news.getTitle());
            binding.dateTv.setText(String.valueOf(news.getCreatedAt()));


        }
    }


    public interface onItemClickListener {
        void onItemClick(int position);

        void onLongItemClick(int position);
    }
}

