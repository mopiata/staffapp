package dao;

import environments.Constants;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o=new Sql2o(Constants.URL_TEST,Constants.USERNAME,Constants.PASSWORD);
    }

    @Override
    protected void after(){
        try(Connection con=DB.sql2o.open()){
            String deleteDepartmentsQuery="DELETE FROM departments *;";
            String deleteGeneralNewsQuery="DELETE FROM general_news *;";
            String deleteDepartmentNewsQuery="DELETE FROM department_news *;";
            String deleteUsersQuery="DELETE FROM users *;";

            con.createQuery(deleteDepartmentsQuery)
                    .executeUpdate();
            con.createQuery(deleteGeneralNewsQuery)
                    .executeUpdate();
            con.createQuery(deleteDepartmentNewsQuery)
                    .executeUpdate();
            con.createQuery(deleteUsersQuery)
                    .executeUpdate();
        }
    }
}
