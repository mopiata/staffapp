package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void add(News news);

    //READ AND LIST
    List<News> getAll();
    News findById(int id);

//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
//    //DELETE
//    void deleteById(int id);
//    void clearAllEngineers();
}
