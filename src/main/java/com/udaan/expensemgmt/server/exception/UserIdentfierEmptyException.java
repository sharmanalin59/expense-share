package com.udaan.expensemgmt.server.exception;

/**
 * @author nalin.sharma on 07/09/21
 */
public class UserIdentfierEmptyException extends RuntimeException {
    private String mssg;
    public UserIdentfierEmptyException(String m) {
        mssg = m;
    }

    public String getMessage() {
        return mssg;
    }
}
