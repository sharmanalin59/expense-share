package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.ExpenseShare;
import com.udaan.expensemgmt.server.entity.Transaction;
import com.udaan.expensemgmt.server.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserDebtStatusDao {
    public UserDebtStatusDao(UserDao userDao) {
        this.userDao = userDao;
    }
    static Map<User, Map<User, List<ExpenseShare>>> userDebtStatusMap = new HashMap<>();
    UserDao userDao;

    void addToUserDebtStatus(String fromEmail, String toEmail, ExpenseShare expenseShare) {
        User fromUser = userDao.getUser(fromEmail);
        User toUser = userDao.getUser(toEmail);
        if(fromUser == null || toUser == null || fromEmail.equals(toEmail)) {
            throw new RuntimeException("From or To user is invalid");
        }
        if(!userDebtStatusMap.containsKey(fromUser)) {
            userDebtStatusMap.put(userDao.getUser(fromEmail), new HashMap<>());
        }
        if(!userDebtStatusMap.get(fromUser).containsKey(toUser)) {
            userDebtStatusMap.get(fromUser).put(toUser, new ArrayList<>());
        }
        userDebtStatusMap.get(fromUser).get(toUser).add(expenseShare);
    }
    public BigDecimal userOneInDebtBy(String userEmail1, String userEmail2) {
        User user1 = userDao.getUser(userEmail1);
        User user2 = userDao.getUser(userEmail2);
        if(user1 == null || user2 == null || userEmail1.equals(userEmail2)) {
            throw new RuntimeException("user1 or user2 is invalid");
        }
        double userTwoOwesUserOne = userDebtStatusMap.get(user1).get(user2).stream()
                .map(es -> (es.getTotalShare().subtract(es.getPaidShare())).doubleValue())
                .mapToDouble(d->d).sum();

        double userOneOwesUserTwo = userDebtStatusMap.get(user2).get(user1).stream()
                .map(es -> (es.getTotalShare().subtract(es.getPaidShare())).doubleValue())
                .mapToDouble(d->d).sum();
        System.out.println("userTwoOwesUserOne "+userTwoOwesUserOne);
        System.out.println("userOneOwesUserTwo "+userOneOwesUserTwo);
        if(userTwoOwesUserOne > userOneOwesUserTwo) {
            System.out.println("total userTwoOwesUserOne "+(userTwoOwesUserOne - userOneOwesUserTwo));
        }
        else if(userTwoOwesUserOne < userOneOwesUserTwo) {
            System.out.println("total userOneOwesUserTwo "+(userOneOwesUserTwo - userTwoOwesUserOne));
        }
        else {
            System.out.println("both users are balanced "+(userOneOwesUserTwo - userTwoOwesUserOne));
        }
        return new BigDecimal(userOneOwesUserTwo - userTwoOwesUserOne);
    }

    public List<ExpenseShare> showPendingExpenseShareForUserInDebtBy(String userEmail1, String userEmail2) {
        User user1 = userDao.getUser(userEmail1);
        User user2 = userDao.getUser(userEmail2);
        BigDecimal diff = userOneInDebtBy(userEmail1, userEmail2);
        if(diff.doubleValue() == 0) {
            return null;
        }
        else if(diff.doubleValue() < 0) {
            return userDebtStatusMap.get(user1).get(user2).stream()
                    .filter(es -> (es.getTotalShare().subtract(es.getPaidShare())).doubleValue() > 0)
                    .collect(Collectors.toList());
        }
        return userDebtStatusMap.get(user2).get(user1).stream()
                .filter(es -> (es.getTotalShare().subtract(es.getPaidShare())).doubleValue() > 0)
                .collect(Collectors.toList());
    }

    List<ExpenseShare> showAllExpenseShareForUserInDebtBy(String userEmail1, String userEmail2) {
        User user1 = userDao.getUser(userEmail1);
        User user2 = userDao.getUser(userEmail2);
        BigDecimal diff = userOneInDebtBy(userEmail1, userEmail2);
        if(diff.doubleValue() == 0) {
            return null;
        }
        else if(diff.doubleValue() < 0) {
            return new ArrayList<>(userDebtStatusMap.get(user1).get(user2));
        }
        return new ArrayList<>(userDebtStatusMap.get(user2).get(user1));
    }
}
