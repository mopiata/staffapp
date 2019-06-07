import dao.Sql2oDepartmentDao;
import dao.Sql2oDepartmentNewsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import dao.DB;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);
        Sql2oDepartmentNewsDao departmentNewsDao=new Sql2oDepartmentNewsDao(DB.sql2o);
        Sql2oUserDao userDao=new Sql2oUserDao(DB.sql2o);
        Sql2oNewsDao newsDao=new Sql2oNewsDao(DB.sql2o);
//        Connection conn;
//        conn=DB.sql2o.open();
        Gson gson=new Gson();

        get("/departments","application/json",(request, response) -> {
            if(departmentDao.getAll().size()>0){
                return gson.toJson(departmentDao.getAll());
            }else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });

        get("/departments/:id","application/json",(request, response) -> {
            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                return gson.toJson(departmentDao.findById(Integer.parseInt(request.params("id"))));
            }else{
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        post("/departments/new","application/json", (request, response) -> {
            Department department=gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            return  gson.toJson(department);
        });

        get("/users","application/json",(request, response) -> {
            if(userDao.getAll().size()>0){
                return gson.toJson(userDao.getAll());
            }else{
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        get("/users/:id","application/json",(request, response) -> {
            if(userDao.findById(Integer.parseInt(request.params("id")))!=null){
                return gson.toJson(userDao.findById(Integer.parseInt(request.params("id"))));
            }else{
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        post("/users/new","application/json", (request, response) -> {
            User user=gson.fromJson(request.body(), User.class);
            userDao.add(user);
//            Department department=departmentDao.findById(user.getDepartmentId());
//            department.setEmployeeCount(departmentDao.userCountPerDepartment(user.getDepartmentId()));
            response.status(201);
            return  gson.toJson(user);
        });

        get("/generalnews","application/json",(request, response) -> {
            if(newsDao.getAll().size()>0){
                return gson.toJson(newsDao.getAll());
            }else{
                return "{\"message\":\"I'm sorry, but no staff news is currently listed in the database.\"}";
            }
        });

        get("/generalnews/:id","application/json",(request, response) -> {
            if(newsDao.findById(Integer.parseInt(request.params("id")))!=null){
                return gson.toJson(newsDao.findById(Integer.parseInt(request.params("id"))));
            }else{
                return "{\"message\":\"I'm sorry, but no staff news is currently listed in the database.\"}";
            }
        });

        post("/generalnews/new","application/json", (request, response) -> {
            News news=gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.status(201);
            return  gson.toJson(news);
        });

        get("/departmentnews/:id","application/json",(request, response) -> {
            if(departmentNewsDao.getAllByDepartmentId(Integer.parseInt(request.params("id"))).size()>0){
                return gson.toJson(departmentNewsDao.getAllByDepartmentId(Integer.parseInt(request.params("id"))));
            }else{
                return "{\"message\":\"I'm sorry, but no staff news is currently listed in the database.\"}";
            }
        });

        get("/departmentnews/:id/:newsid","application/json",(request, response) -> {
            if(departmentNewsDao.getAllByDepartmentId(Integer.parseInt(request.params("id"))).size()>0 && departmentNewsDao.findById(Integer.parseInt(request.params("newsid")))!=null){
                return gson.toJson(departmentNewsDao.findById(Integer.parseInt(request.params("newsid"))));
            }else{
                return "{\"message\":\"I'm sorry, but no staff news is currently listed in the database.\"}";
            }
        });

        post("/departmentnews/new","application/json", (request, response) -> {
            DepartmentNews news=gson.fromJson(request.body(), DepartmentNews.class);
            departmentNewsDao.add(news);
            response.status(201);
            return  gson.toJson(news);
        });

        //filters
        after((request, response) ->{
            response.type("application/json");
        } );
    }
}
