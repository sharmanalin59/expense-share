package com.udaan.expensemgmt.server.endpoint;

import com.udaan.expensemgmt.server.compute.BaseComputeStrategy;
import com.udaan.expensemgmt.server.compute.Strategy;
import com.udaan.expensemgmt.server.dao.ExpenseDao;
import com.udaan.expensemgmt.server.dao.ExpenseShareDao;
import com.udaan.expensemgmt.server.dao.UserDao;
import com.udaan.expensemgmt.server.dao.UserExpenseShareDao;
import com.udaan.expensemgmt.server.entity.Expense;
import com.udaan.expensemgmt.server.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class ExpenseEndpoints {
    ExpenseDao expenseDao = new ExpenseDao();
    UserDao userDao = new UserDao();

    public void createExpense(String total, String email, String curr, Strategy strategy) {
        User user = userDao.getUser(email);
        if(user == null) {
            throw new RuntimeException("Invalid user");
        }
        if(new BigDecimal(total).doubleValue() <= 0 ) {
            throw new RuntimeException("Invalid total share");
        }
        expenseDao.createExpense(new Expense(new BigDecimal(total), userDao.getUser(email), curr, strategy));
    }

    public Expense getExpense(String expenseId) {
        return expenseDao.getExpense(Integer.parseInt(expenseId));
    }

    public List<Expense> getExpenseList() {
        return expenseDao.getExpenseList();
    }
}
