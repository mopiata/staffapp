package dao;

import models.Department;
import models.User;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {

    private static Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);
    private Sql2oUserDao userDao=new Sql2oUserDao(DB.sql2o);

    public Department addDepartment() {
        return new Department("Brand and Marketing", "Handles marketing and brand image for the company");
    }

    public Department otherDepartment(){
        return new Department("NPD", "Handles network planning and delivery");
    }

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void addDepartmentSetsId() throws Exception {
        Department department=addDepartment();
        int originalId=department.getId();

        departmentDao.add(department);

        assertNotEquals(originalId,department.getId());
    }

    @Test
    public void getAllReturnsAllDepartments() throws Exception {
        Department department=addDepartment();
        Department otherDepartment=otherDepartment();

        departmentDao.add(department);
        departmentDao.add(otherDepartment);

        List<Department> departments=departmentDao.getAll();

        assertTrue(departments.size()==2);
        assertTrue(departments.contains(department));
        assertTrue(departments.contains(otherDepartment));

    }

    @Test
    public void existingDepartmentsAreFoundById() throws Exception {
        Department department=addDepartment();
        departmentDao.add(department);

        Department foundDepartment=departmentDao.findById(department.getId());
        assertEquals(department, foundDepartment);
    }

    @Test
    public void getUsersReturnsAllDepartmentUsers() throws Exception {
        Department department=addDepartment();
        departmentDao.add(department);
        Department otherDepartment=otherDepartment();
        departmentDao.add(otherDepartment);

        User newUser=new User("Evans Matunda",5555,department.getId(),"Engineer, VAS", "Taking care of values added services");
        User otherUser=new User("Ezra Omondi",7394,otherDepartment.getId(),"Senior Officer, Learning", "Planning for and organizing learning for division;");
        userDao.add(newUser);
        userDao.add(otherUser);

        assertTrue(departmentDao.getUsers(department.getId()).contains(newUser));
        assertFalse(departmentDao.getUsers(department.getId()).contains(otherUser));
    }

    @Test
    public void updateEmployeeCountSetsCount() {
        Department department=addDepartment();
        departmentDao.add(department);
        User user=new User("Evans Matunda",5555,department.getId(),"Engineer, VAS", "Taking care of values added services");
        userDao.add(user);
        int count = departmentDao.getUsers(user.getDepartmentId()).size();
        department.setEmployeeCount(count);
        int depId = user.getDepartmentId();
        departmentDao.updateEmployeeCount(depId, count);

        System.out.println(department.getEmployeeCount());
        assertTrue(department.getEmployeeCount()==1);
    }

    @Test
    public void deleteByIdDeletesDepartment() {
        Department department=addDepartment();
        departmentDao.add(department);
        departmentDao.deleteById(department.getId());

        assertTrue(departmentDao.getAll().size()==0);
    }

    @Test
    public void clearDeletesAllDepartment() {
        Department department=addDepartment();
        Department otherDepartment=otherDepartment();
        departmentDao.add(department);
        departmentDao.add(otherDepartment);
        int originalSize=departmentDao.getAll().size();

        departmentDao.clearAllDepartments();

        assertFalse(departmentDao.getAll().size()==originalSize);
    }
}