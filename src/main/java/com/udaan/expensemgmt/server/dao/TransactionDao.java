package com.udaan.expensemgmt.server.dao;

import com.udaan.expensemgmt.server.entity.Transaction;
import com.udaan.expensemgmt.server.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nalin.sharma on 08/09/21
 */
public class TransactionDao {
    static Map<Integer, Transaction> userTransaction = new HashMap<>();

    void addToTransaction(Transaction transaction) {
        userTransaction.put(transaction.getExpenseShare().getExpenseShareId(), transaction);
    }
    List<Transaction> getTransactionList() {
        return new ArrayList<>(userTransaction.values());
    }
}
