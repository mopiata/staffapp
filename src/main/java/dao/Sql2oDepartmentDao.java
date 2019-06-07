package dao;

import models.Department;
import models.User;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;
//    Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);

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


//    public void userCountPerDepartment(int departmentid){
//        String sql="SELECT * FROM users WHERE departmentid=:departmentId;";
//        String departmentFind="SELECT * FROM departments WHERE id=:departmentId;";
//
//
//        List<User> departmentUsers=new ArrayList<>();
//        List<Department> departments=new ArrayList<>();
//
//        try(Connection con=DB.sql2o.open()) {
//            departmentUsers=con.createQuery(sql)
//                    .addParameter("departmentId",departmentid)
//                    .executeAndFetch(User.class);
//
//            departments.add(con.createQuery(departmentFind)
//            .executeAndFetchFirst(Department.class));
//
//            departments.get(0).setEmployeeCount(departmentUsers.size());
//
//        }
//    }
//
//    public void addEmployeeCount(Department department){
//        int newcount=department.getEmployeeCount()+1;
//        department.setEmployeeCount(newcount);
//
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "UPDATE departments SET employeecount = :employeeCount WHERE departmentid=:departmentId;";
//            con.createQuery(sql)
//                    .bind(department)
//                    .executeUpdate();
//        }
//    }

    @Override
    public List<User> getUsers(int id){
        String sql="SELECT * FROM users WHERE departmentid=:id;";

        try(Connection con=DB.sql2o.open()){
            return  con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void updateEmployeeCount(int id, int employeeCount){

        String sql="UPDATE departments SET employeecount = :employeeCount WHERE id=:id;";

        try(Connection con=DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("employeeCount",employeeCount)
                    .executeUpdate();
        }
    }

}
