package models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private int eknumber;
    private int departmentId;
    private String position;
    private String role;

    public User(String name, int eknumber, int departmentId, String position, String role){
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
        User user = (User) o;
        return getId() == user.getId() &&
                getEknumber() == user.getEknumber() &&
                getDepartmentId() == user.getDepartmentId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEknumber(), getDepartmentId(), getPosition(), getRole());
    }
}
