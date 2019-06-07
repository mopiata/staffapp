package dao;

import models.Department;
import models.News;
import org.junit.Test;
import org.junit.Rule;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    public static Sql2oNewsDao newsDao=new Sql2oNewsDao(DB.sql2o);

    //helper methods
    public News addNews(){
        return new News("Masoko","Grab sale items today!!");
    }

    public News otherNews(){
        return new News("Congratulations","Welcome Maggie, our new CTO");
    }

    public Department addDepartment(){
        return new Department("NPD","Handles network planning and delivery");
    }

    @Rule
    public DatabaseRule database=new DatabaseRule();

    @Test
    public void addSetsNewsId() throws Exception {
        News generalNews=addNews();
        int originalId=generalNews.getId();
        newsDao.add(generalNews);
        assertNotEquals(originalId,generalNews.getId());
    }

    @Test
    public void getAllReturnsAllGeneralNews() throws Exception {
        News news=addNews();
        News otherNews=otherNews();

        newsDao.add(news);
        newsDao.add(otherNews);

        List<News> newsList=newsDao.getAll();


        assertTrue(newsList.size()==2);
        assertTrue(newsList.contains(news));
        assertTrue(newsList.contains(otherNews));
    }

    @Test
    public void findByIdReturnsTheNewsItem() throws Exception {
        News news=addNews();
        newsDao.add(news);

        News foundNews=newsDao.findById(news.getId());
        System.out.println(news.getId());
        assertEquals(news, foundNews);
    }

    @Test
    public void deleteByIdDeletesDepartment() {
        News news=addNews();
        newsDao.add(news);
        newsDao.deleteById(news.getId());

        assertTrue(newsDao.getAll().size()==0);
    }

    @Test
    public void clearDeletesAllDepartment() {
        News news=addNews();
        News otherNews=otherNews();

        newsDao.add(news);
        newsDao.add(otherNews);

        int originalSize=newsDao.getAll().size();

        newsDao.clearAllNews();

        assertFalse(newsDao.getAll().size()==originalSize);
    }
}