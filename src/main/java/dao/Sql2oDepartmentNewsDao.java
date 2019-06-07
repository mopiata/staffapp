package dao;

import models.DepartmentNews;
import org.sql2o.*;
import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao {
    private final Sql2o sql2o;

    public Sql2oDepartmentNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(DepartmentNews news){
        String sql="INSERT INTO department_news (title, content, createdat, departmentid) VALUES (:title,:content,:createdat, :departmentId);";

        try (Connection con=DB.sql2o.open()){
            int id=(int) con.createQuery(sql,true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();

            news.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
//    public List<DepartmentNews> getAll(){
//        String sql="SELECT * FROM department_news;";
//
//        try (Connection con=DB.sql2o.open()){
//            return con.createQuery(sql)
//                    .executeAndFetch(DepartmentNews.class);
//        }
//    }

    @Override
    public DepartmentNews findById(int id){
        String sql="SELECT * FROM department_news WHERE id=:id;";

        try (Connection con=DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public List<DepartmentNews> getAllByDepartmentId(int departmentid){
        String sql="SELECT * FROM department_news WHERE departmentid=:departmentid;";

        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("departmentid",departmentid)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public void deleteById(int id){
        String sql="DELETE FROM department_news WHERE id=:id;";

        try(Connection con=DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAllNews(){
        String sql="DELETE FROM department_news *;";

        try(Connection con=DB.sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    @Override
    public List<DepartmentNews> getAll(){
        String sql="SELECT * FROM department_news;";

        try (Connection con=DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

}
