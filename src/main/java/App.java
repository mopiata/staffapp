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
                return "{\"message\":\"I'm sorry, but no restaurants are currently listed in the database.\"}";
            }
    });

        //filters
        after((request, response) ->{
            response.type("application/json");
        } );
    }
}
