package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao{
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(News news){
        String sql="INSERT INTO general_news (title, content, createdat) VALUES (:title,:content,:createdat);";

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

    @Override
    public List<News> getAll(){
        String sql="SELECT * FROM general_news;";

        try (Connection con=DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public News findById(int id){
        String sql="SELECT * FROM general_news WHERE id=:id;";

        try (Connection con=DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(News.class);
        }
    }

}
