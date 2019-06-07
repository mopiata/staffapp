package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    //CREATE
    void add(Department department);

    //READ AND LIST
    List<Department> getAll();
    Department findById(int id);
//    void userCountPerDepartment(Department department);

//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
//    //DELETE
//    void deleteById(int id);
//    void clearAllEngineers();
}
