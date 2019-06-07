package dao;

import models.Department;
import models.DepartmentNews;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {

    private static Sql2oDepartmentNewsDao departmentNewsDao=new Sql2oDepartmentNewsDao(DB.sql2o);
    private static Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);

    //helper methods
    public Department addDepartment() {
        return new Department("Brand and Marketing", "Handles marketing and brand image for the company");
    }

    public Department otherDepartment(){
        return new Department("NPD", "Handles network planning and delivery");
    }


    public DepartmentNews addDepartmentNews(){
        Department department=addDepartment();
        departmentDao.add(department);
        return new DepartmentNews("Fumigation Alert","There will be a three day fumigation period beginning tomorrow",department.getId());
    }

    public DepartmentNews otherDepartmentNews(){
        Department otherDepartment=otherDepartment();
        departmentDao.add(otherDepartment);
        return new DepartmentNews("Public Holiday", "Take the day off, you deserve it",otherDepartment.getId());
    }


    @Rule
    public DatabaseRule database=new DatabaseRule();

    @Test
    public void addNewsSetsId() throws Exception{
        DepartmentNews departmentNews=addDepartmentNews();
        int originalId=departmentNews.getId();

        departmentNewsDao.add(departmentNews);

        assertNotEquals(originalId, departmentNews.getId());
    }

    @Test
    public void getAllByDepartmentReturnsAllDepartmentNewsInDepartment() throws Exception {
        DepartmentNews departmentNews=addDepartmentNews();
        DepartmentNews otherDepartmentNews=otherDepartmentNews();

        departmentNewsDao.add(departmentNews);
        departmentNewsDao.add(otherDepartmentNews);

        List<DepartmentNews> departmentNewsList=departmentNewsDao.getAllByDepartmentId(departmentNews.getDepartmentId());
        assertTrue(departmentNewsList.size()==1);
        assertTrue(departmentNewsList.contains(departmentNews));
    }

    @Test
    public void findById() {
        DepartmentNews departmentNews=addDepartmentNews();
        departmentNewsDao.add(departmentNews);

        DepartmentNews foundNews=departmentNewsDao.findById(departmentNews.getId());
        assertEquals(foundNews,departmentNews);
    }
}