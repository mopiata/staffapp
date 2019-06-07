package dao;
import models.User;
import java.util.List;

public interface UserDao {

    //CREATE
    void add(User user);

    //READ AND LIST
    List<User> getAll();
    User findById(int id);


//    //UPDATE
//    void update(int id, int ekNumber, String name, String phone);
//
//    //DELETE
    void deleteById(int id);
    void clearAllUsers();
}
