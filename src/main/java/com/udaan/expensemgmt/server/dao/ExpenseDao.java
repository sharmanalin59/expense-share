package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.Expense;
import com.udaan.expensemgmt.server.exception.InvalidExpenseIdException;

import java.util.*;

/**
 * @author nalin.sharma on 08/09/21
 */
public class ExpenseDao {
    static int expenseId = 1;
    static Map<Integer, Expense> expenseMap = new HashMap<>();

    public List<Expense> getExpenseList() {
        return new ArrayList<>(expenseMap.values());
    }

    public void createExpense(Expense expense) {
        expense.setExpenseId(expenseId);
        expenseMap.put(expenseId++, expense);
    }

    public void deleteExpense(int expenseId) {
        if(expenseMap.containsKey(expenseId)) {
            expenseMap.get(expenseId).setDeleted(true);
        }
    }

    public Expense getExpense(int expenseId) {
        return expenseMap.getOrDefault(expenseId, null);
    }

    /*void updateExpense(int expenseIdReq, Expense expense) {
        if(expenseIdReq <=0 && expenseIdReq > expenseId) {
            throw new InvalidExpenseIdException("Invalid expense ID");
        }
        expenseMap.add(expense);
    }*/
}
