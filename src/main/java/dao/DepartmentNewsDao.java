package dao;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    //CREATE
    void add(DepartmentNews news);

    //READ AND LIST
    List<DepartmentNews> getAllByDepartmentId(int departmentid);
    DepartmentNews findById(int id);
    List<DepartmentNews> getAll();

//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
    //DELETE
    void deleteById(int id);
    void clearAllNews();
}
