package com.udaan.expensemgmt.server.endpoint;

import com.udaan.expensemgmt.server.dao.ExpenseDao;
import com.udaan.expensemgmt.server.dao.ExpenseShareDao;
import com.udaan.expensemgmt.server.dao.UserDao;
import com.udaan.expensemgmt.server.dao.UserExpenseShareDao;
import com.udaan.expensemgmt.server.entity.ExpenseShare;
import com.udaan.expensemgmt.server.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class ExpenseShareEndpoints {

    UserExpenseShareDao userExpenseShareDao = new UserExpenseShareDao();
    ExpenseShareDao expenseShareDao = new ExpenseShareDao(userExpenseShareDao);
    ExpenseDao expenseDao = new ExpenseDao();
    UserDao userDao = new UserDao();

    public void createExpenseShare(String email, String totalShare, String curr, String expenseId) {
        User user = userDao.getUser(email);
        if(user == null) {
            throw new RuntimeException("Invalid user");
        }
        if(new BigDecimal(totalShare).doubleValue() <= 0 ) {
            throw new RuntimeException("Invalid total share");
        }
        ExpenseShare expenseShare =
                new ExpenseShare(userDao.getUser(email), new BigDecimal(totalShare),
                        curr, expenseDao.getExpense(Integer.parseInt(expenseId)));
        expenseShareDao.createExpenseShare(expenseShare);
    }

    public ExpenseShare getExpenseShare(String expenseShareIdReq) {
        return expenseShareDao.getExpenseShare(Integer.parseInt(expenseShareIdReq));
    }

    public List<ExpenseShare> getExpenseShareList() {
        return expenseShareDao.getExpenseShareList();
    }
}
