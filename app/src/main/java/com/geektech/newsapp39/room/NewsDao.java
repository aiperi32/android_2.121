package com.geektech.newsapp39.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.newsapp39.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();


    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News> sortAll();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

}
