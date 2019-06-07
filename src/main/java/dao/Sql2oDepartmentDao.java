package dao;

import models.Department;
import models.User;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;

    public  Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }


    @Override
    public void add(Department department){
        String sql="INSERT INTO departments (name,description, employeecount) VALUES (:name, :description, :employeeCount);";

        try(Connection con=DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();

            department.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAll(){
        try(Connection con=DB.sql2o.open()){
            return con.createQuery("SELECT * FROM departments;")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public Department findById(int id){
        try(Connection con=DB.sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Department.class);
        }
    }


//    public int userCountPerDepartment(int departmentid){
//        String sql="SELECT * FROM users WHERE id=:departmentId;";
//        String departmentQuery="UPDATE departments SET employeecount = :employeecount WHERE departmentid=:departmentId;";
//
//        List<User> departmentUsers=new ArrayList<>();
//
//        try(Connection con=DB.sql2o.open()) {
//            departmentUsers=con.createQuery(sql)
//                    .addParameter("departmentid",departmentid)
//                    .executeAndFetch(User.class);
//
//            con.createQuery(departmentQuery)
//                    .addParameter("departmentid",departmentid)
//                    .executeUpdate();
//
//            return departmentUsers.size();
//        }
//    }

    public void addEmployeeCount(Department department){
        int newcount=department.getEmployeeCount()+1;
        department.setEmployeeCount(newcount);

        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE departments SET employeecount = :employeecount WHERE departmentid=:departmentId;";
            con.createQuery(sql)
                    .bind(department)
                    .executeUpdate();
        }
    }

}
