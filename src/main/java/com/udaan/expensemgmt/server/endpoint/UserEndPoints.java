package com.udaan.expensemgmt.server.endpoint;

import com.udaan.expensemgmt.server.dao.UserDao;
import com.udaan.expensemgmt.server.entity.User;

import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserEndPoints {

    UserDao userDao = new UserDao();

    public void addUser(String name, String email) {
        User user = new User(name, email);
        userDao.addUser(user);
    }
    public List<User> getUserList() {
        return userDao.getUserList();
    }
    public User getUser(String email) {
        return userDao.getUser(email);
    }
}
