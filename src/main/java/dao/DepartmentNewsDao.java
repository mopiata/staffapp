package dao;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    //CREATE
    void add(DepartmentNews news);

    //READ AND LIST
    List<DepartmentNews> getAll();
    DepartmentNews findById(int id);

//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
//    //DELETE
//    void deleteById(int id);
//    void clearAllEngineers();
}
