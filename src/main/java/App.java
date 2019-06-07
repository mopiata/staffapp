import dao.Sql2oDepartmentDao;
import dao.Sql2oDepartmentNewsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import dao.DB;
import models.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import exceptions.ApiException;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process=new ProcessBuilder();
        Integer port;

        if(process.environment().get("PORT")!=null){
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);
        Sql2oDepartmentNewsDao departmentNewsDao=new Sql2oDepartmentNewsDao(DB.sql2o);
        Sql2oUserDao userDao=new Sql2oUserDao(DB.sql2o);
        Sql2oNewsDao newsDao=new Sql2oNewsDao(DB.sql2o);

        Gson gson=new Gson();

        get("/departmentsdeleteall","application/json",(request, response) -> {
            if(departmentDao.getAll().size()>0){
                departmentDao.clearAllDepartments();
                return "{\"message\":\"All departments have been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No departments exists"));
            }
        });

        get("/departments/:id/delete","application/json",(request, response) -> {
            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                departmentDao.deleteById(Integer.parseInt(request.params("id")));
                return "{\"message\":\"Department has been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No department with the id: %s exists", request.params("id")));
            }
        });

        get("/usersdeleteall","application/json",(request, response) -> {
            if(userDao.getAll().size()>0){
                userDao.clearAllUsers();
                return "{\"message\":\"All users have been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No users exists"));
            }
        });

        get("/users/:id/delete","application/json",(request, response) -> {
            if(userDao.findById(Integer.parseInt(request.params("id")))!=null){
                userDao.deleteById(Integer.parseInt(request.params("id")));
                return "{\"message\":\"Department has been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No users with the id: %s exists", request.params("id")));
            }
        });

        get("/generaldeleteall","application/json",(request, response) -> {
            if(newsDao.getAll().size()>0){
                newsDao.clearAllNews();
                return "{\"message\":\"All general news has been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No general news exists"));
            }
        });

        get("/generalnews/:id/delete","application/json",(request, response) -> {
            if(newsDao.findById(Integer.parseInt(request.params("id")))!=null){
                newsDao.deleteById(Integer.parseInt(request.params("id")));
                return "{\"message\":\"News has been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No users with the id: %s exists", request.params("id")));
            }
        });

        get("/departmentnewsdeleteall","application/json",(request, response) -> {
            if(departmentNewsDao.getAll().size()>0){
                departmentNewsDao.clearAllNews();
                return "{\"message\":\"All department news has been cleared.\"}";
            }else{
                throw new ApiException(404, String.format("No users exists"));
            }
        });

        get("/departmentnews/:id/:newsid/delete","application/json",(request, response) -> {
            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                if(departmentNewsDao.findById(Integer.parseInt(request.params("newsid")))!=null ){
                    departmentNewsDao.deleteById(Integer.parseInt(request.params("newsid")));
                    return "{\"message\":\"News has been cleared.\"}";
                }else{
                    throw new ApiException(404, String.format("No news with the id: %s exists in this department", request.params("newsid")));
                }
            }else {
                throw new ApiException(404, String.format("No department with the id: %s exists", request.params("id")));
            }
        });

        get("/departments","application/json",(request, response) -> {
            if(departmentDao.getAll().size()>0){
                return gson.toJson(departmentDao.getAll());
            }else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });

        get("/","application/json",(request, response) -> {
            return "{\"message\":\"Hi, welcome to my API!\"}";
        });

        get("/departments/:id","application/json",(request, response) -> {
            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                return gson.toJson(departmentDao.findById(Integer.parseInt(request.params("id"))));
            }else{
                throw new ApiException(404, String.format("No department with the id: %s exists", request.params("id")));
            }
        });

        get("/departments/:id/users","application/json",(request, response) -> {
            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                if(departmentDao.getUsers(Integer.parseInt(request.params("id"))).size()>0 ){
                    return gson.toJson(departmentDao.getUsers(Integer.parseInt(request.params("id"))));
                }else{
                    return "{\"message\":\"I'm sorry, but no users are currently listed in the database for this department.\"}";
                }
            }else {
                throw new ApiException(404, String.format("No department with the id: %s exists", request.params("id")));
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
                throw new ApiException(404, String.format("No user with the id: %s exists", request.params("id")));
            }
        });

        post("/users/new","application/json", (request, response) -> {
            User user=gson.fromJson(request.body(), User.class);
            if(departmentDao.findById(user.getDepartmentId())!=null){
                userDao.add(user);
                int count = departmentDao.getUsers(user.getDepartmentId()).size();
                int depId = user.getDepartmentId();
                departmentDao.findById(depId).setEmployeeCount(count);
                departmentDao.updateEmployeeCount(depId, count);
            }else{
                throw new ApiException(404, String.format("The department does not exist"));
            }

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
                throw new ApiException(404, String.format("No staff news with the id: %s exists", request.params("id")));
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
                throw new ApiException(404, String.format("No department news with the id: %s exists for this department", request.params("id")));
            }
        });

        get("/departmentnews/:id/:newsid","application/json",(request, response) -> {

            if(departmentDao.findById(Integer.parseInt(request.params("id")))!=null){
                if(departmentNewsDao.findById(Integer.parseInt(request.params("newsid")))!=null ){
                    return gson.toJson(departmentDao.getUsers(Integer.parseInt(request.params("id"))));
                }else{
                    throw new ApiException(404, String.format("No news with the id: %s exists in this department", request.params("newsid")));
                }
            }else {
                throw new ApiException(404, String.format("No department with the id: %s exists", request.params("id")));
            }
        });

        post("/departmentnews/new","application/json", (request, response) -> {
            DepartmentNews news=gson.fromJson(request.body(), DepartmentNews.class);

            if(departmentDao.findById(news.getDepartmentId())!=null){
                departmentNewsDao.add(news);
            }else{
                throw new ApiException(404, String.format("The department does not exist"));
            }

            response.status(201);
            return  gson.toJson(news);
        });

        //filters
        after((request, response) ->{
            response.type("application/json");
        } );

        exception(ApiException.class, (exc,req,res)->{
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonmap = new HashMap<>();
            jsonmap.put("status",err.getStatusCode());
            jsonmap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after doesn't run in case of an exception
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonmap));
        });
    }
}
