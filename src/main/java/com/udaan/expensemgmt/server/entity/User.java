package com.udaan.expensemgmt.server.entity;

import com.udaan.expensemgmt.server.Utils;
import com.udaan.expensemgmt.server.exception.UserIdentfierEmptyException;

import java.util.Locale;

/**
 * @author nalin.sharma on 07/09/21
 */
public class User {
    private String name;
    private String email;
    private Locale locale;
    private String preferredCurrency;

    public User(String name, String email) {
        if("".equals(name)) {
           throw new UserIdentfierEmptyException("Name must not be empty");
        }
        if("".equals(email)) {
            throw new UserIdentfierEmptyException("Email must not be empty");
        }
        if(!Utils.validate(email)) {
            throw new UserIdentfierEmptyException("Email must be valid");
        }
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", locale=" + locale +
                ", preferredCurrency='" + preferredCurrency + '\'' +
                '}';
    }
}
