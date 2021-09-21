package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.Expense;
import com.udaan.expensemgmt.server.entity.ExpenseShare;
import com.udaan.expensemgmt.server.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nalin.sharma on 08/09/21
 */
public class ExpenseShareDao {
    UserDao userDao = new UserDao();
    UserExpenseShareDao userExpenseShareDao = new UserExpenseShareDao();
    UserDebtStatusDao userDebtStatusDao = new UserDebtStatusDao(userDao);

    static int expenseShareId = 1;
    static Map<Integer, ExpenseShare> expenseShareMap = new HashMap<>();

    public ExpenseShareDao(UserExpenseShareDao userExpenseShareDaoReq) {
        userExpenseShareDao = userExpenseShareDaoReq;
    }

    public List<ExpenseShare> getExpenseShareList() {
        return new ArrayList<>(expenseShareMap.values());
    }

    public void createExpenseShare(ExpenseShare expenseShare) {
        /*if(expenseShare.getExpense().getComputeStrategy().compute(expenseShare.getExpense().getTotal(), expenseShare.getFactor())) {

        }*/
        expenseShare.setExpenseShareId(expenseShareId);
        expenseShareMap.put(expenseShareId, expenseShare);
        expenseShare.getExpense().addToExpenseShareMap(expenseShare);
        userExpenseShareDao.addToUserExpenseShare(expenseShare);
        userDebtStatusDao.addToUserDebtStatus(expenseShare.getExpense().getCreatedBy().getEmail(),
                expenseShare.getUser().getEmail(),
                expenseShare
        );
        expenseShareId++;
    }

    public ExpenseShare getExpenseShare(Integer expenseShareIdReq) {
        return expenseShareMap.getOrDefault(expenseShareIdReq, null);
    }
}
