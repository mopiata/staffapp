package dao;

import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //CREATE
    void add(Department department);

    //READ AND LIST
    List<Department> getAll();
    Department findById(int id);
//    void userCountPerDepartment(Department department);
    List<User> getUsers(int id);
    void updateEmployeeCount(int departmentid, int employeecount);

//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
//    //DELETE
//    void deleteById(int id);
//    void clearAllEngineers();
}
