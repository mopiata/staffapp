package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private int departmentId;
    private String name;
    private int employeeCount;
    private String description;

    public Department(String name, String description){
        this.name=name;
        this.description=description;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getDepartmentId() == that.getDepartmentId() &&
                getEmployeeCount() == that.getEmployeeCount() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentId(), getName(), getEmployeeCount(), getDescription());
    }
}
