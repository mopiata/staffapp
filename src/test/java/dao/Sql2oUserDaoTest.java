package dao;

import models.Department;
import models.User;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);
    private Sql2oUserDao userDao=new Sql2oUserDao(DB.sql2o);

    //helpers
    public Department addDepartment() {
        return new Department("Brand and Marketing", "Handles marketing and brand image for the company");
    }

    public Department otherDepartment(){
        return new Department("NPD", "Handles network planning and delivery");
    }

    public User addUser(){
        Department department=addDepartment();
        departmentDao.add(department);
        return new User("Evans Matunda",5555,department.getId(),"Engineer, VAS", "Taking care of values added services");
    }

    public User otherUser(){
        Department otherDepartment=otherDepartment();
        departmentDao.add(otherDepartment);
        return new User("Ezra Omondi",7394,otherDepartment.getId(),"Senior Officer, Learning", "Planning for and organizing learning for division;");
    }


    @Rule
    public DatabaseRule database=new DatabaseRule();

    @Test
    public void addNewsSetsId() throws Exception{
        User user=addUser();
        int originalId=user.getId();

        userDao.add(user);

        assertNotEquals(originalId, user.getId());
    }

    @Test
    public void getAllReturnsAllDepartmentNews() throws Exception {
        User user=addUser();
        User otherUser=otherUser();

        userDao.add(user);
        userDao.add(otherUser);

        List<User> users=userDao.getAll();
        assertTrue(users.size()==2);
        assertTrue(users.contains(user));
        assertTrue(users.contains(otherUser));

    }

    @Test
    public void findById() {
        User user=addUser();
        userDao.add(user);

        User foundUser=userDao.findById(user.getId());

        assertEquals(foundUser,user);
    }


}