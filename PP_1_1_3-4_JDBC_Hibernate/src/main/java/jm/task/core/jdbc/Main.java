package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        //create table
        userService.createUsersTable();
        //add 4 users -> sout:user added to db
        userService.saveUser("Boris" ,"Britva", (byte) 20);
        userService.saveUser("Sergey","Tykva", (byte)  25);
        userService.saveUser("Vladimir","Brykva", (byte)  35);
        userService.saveUser("Iosif","Bratka", (byte)  30);
        //all users to console
        userService.getAllUsers();
        //wipe table
        userService.cleanUsersTable();
        //delete table
        userService.dropUsersTable();
    }
}
