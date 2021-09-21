package com.udaan.expensemgmt.server.endpoint;

import com.udaan.expensemgmt.server.dao.*;
import com.udaan.expensemgmt.server.entity.ExpenseShare;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserDebtStatusEndpoints {

    UserDao userDao = new UserDao();
    UserDebtStatusDao userDebtStatusDao = new UserDebtStatusDao(userDao);

    public BigDecimal userOneInDebtBy(String userOneEmail, String userTwoEmail) {
        return userDebtStatusDao.userOneInDebtBy(userOneEmail, userTwoEmail);
    }

    public List<ExpenseShare> showPendingExpenseShareForUserInDebtBy(String userOneEmail, String userTwoEmail) {
        return userDebtStatusDao.showPendingExpenseShareForUserInDebtBy(userOneEmail, userTwoEmail);
    }

    public List<ExpenseShare> showAllExpenseShareForUserInDebtBy(String userOneEmail, String userTwoEmail) {
        return userDebtStatusDao.showPendingExpenseShareForUserInDebtBy(userOneEmail, userTwoEmail);
    }
}
