package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.ExpenseShare;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nalin.sharma on 08/09/21
 */
public class UserExpenseShareDao {
    static Map<String, Map<Integer, ExpenseShare>> userExpenseShareMap = new HashMap<>();

    void addToUserExpenseShare(ExpenseShare expenseShare) {
        if(!userExpenseShareMap.containsKey(expenseShare.getUser().getEmail())) {
            userExpenseShareMap.put(expenseShare.getUser().getEmail(), new HashMap<>());
        }
        userExpenseShareMap.get(expenseShare.getUser().getEmail()).put(expenseShare.getExpenseShareId(), expenseShare);
    }

    public List<ExpenseShare> getAllUserExpenseShare(String email) {
        return new ArrayList<>(userExpenseShareMap.getOrDefault(email, new HashMap<>()).values());
    }

    public List<ExpenseShare> getPendingUserExpenseShare(String email) {
        return userExpenseShareMap.getOrDefault(email, new HashMap<>())
                .values()
                .stream()
                .filter(es -> es.getTotalShare().compareTo(es.getPaidShare()) > 0)
                .collect(Collectors.toList());
    }

    public List<ExpenseShare> getUserPaidExpenseShare(String email) {
        return userExpenseShareMap.getOrDefault(email, new HashMap<>())
                .values()
                .stream()
                .filter(es -> es.getTotalShare().compareTo(es.getPaidShare()) == 0)
                .collect(Collectors.toList());
    }

    public ExpenseShare payExpenseShare(BigDecimal expenseShareAmt, int expenseShareIdReq, String email) {
        if(!userExpenseShareMap.containsKey(email)) {
            throw new RuntimeException("Unauthorized to pay");
        }
        Map<Integer, ExpenseShare> expenseShareMap = userExpenseShareMap.getOrDefault(email, null);
        if(!expenseShareMap.containsKey(expenseShareIdReq)) {
            throw new RuntimeException("Expense share not found");
        }

        return expenseShareMap.get(expenseShareIdReq).payExpenseShare(expenseShareAmt);
    }
}
