package com.udaan.expensemgmt.server.endpoint;

import com.udaan.expensemgmt.server.dao.ExpenseDao;
import com.udaan.expensemgmt.server.dao.ExpenseShareDao;
import com.udaan.expensemgmt.server.dao.UserExpenseShareDao;
import com.udaan.expensemgmt.server.entity.ExpenseShare;

import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserExpenseShareEndpoints {

    ExpenseDao expenseDao = new ExpenseDao();
    UserExpenseShareDao userExpenseShareDao = new UserExpenseShareDao();
    ExpenseShareDao expenseShareDao = new ExpenseShareDao(userExpenseShareDao);

    public List<ExpenseShare> getAllUserExpenseShare(String email) {
        return userExpenseShareDao.getAllUserExpenseShare(email);
    }

    public List<ExpenseShare> getPendingUserExpenseShare(String email) {
        return userExpenseShareDao.getPendingUserExpenseShare(email);
    }

    public List<ExpenseShare> getUserPaidExpenseShare(String email) {
        return userExpenseShareDao.getUserPaidExpenseShare(email);
    }
}
