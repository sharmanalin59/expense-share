package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserDao {
    static Map<String, User> userMap = new HashMap<>();

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
    }
    public List<User> getUserList() {
        return new ArrayList<>(userMap.values());
    }
    public User getUser(String email) {
        return userMap.getOrDefault(email,null);
    }
}
