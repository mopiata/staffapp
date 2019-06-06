package dao;

import models.Department;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {

    private static Sql2oDepartmentDao departmentDao=new Sql2oDepartmentDao(DB.sql2o);

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
}