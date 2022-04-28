package crudapp.service;

import crudapp.models.User;

import java.util.List;

public interface UserService {
    User showUser(int id);
    void save(User user);
    List<User> users();
    void delete(int id);
    void update(int id, User user);
}
