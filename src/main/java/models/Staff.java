package models;

import java.util.Objects;

public class Staff {
    private int id;
    private String name;
    private int eknumber;
    private int departmentId;
    private String position;
    private String role;

    public Staff(String name, int eknumber, int departmentId, String position, String role){
        this.name=name;
        this.eknumber=eknumber;
        this.departmentId=departmentId;
        this.position=position;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEknumber() {
        return eknumber;
    }

    public void setEknumber(int eknumber) {
        this.eknumber = eknumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return getId() == staff.getId() &&
                getEknumber() == staff.getEknumber() &&
                getDepartmentId() == staff.getDepartmentId() &&
                Objects.equals(getName(), staff.getName()) &&
                Objects.equals(getPosition(), staff.getPosition()) &&
                Objects.equals(getRole(), staff.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEknumber(), getDepartmentId(), getPosition(), getRole());
    }
}
