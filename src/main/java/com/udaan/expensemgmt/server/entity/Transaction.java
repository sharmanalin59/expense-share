package com.udaan.expensemgmt.server.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author nalin.sharma on 08/09/21
 */
public class Transaction {
    User from;
    User to;
    LocalDateTime madeOn;
    BigDecimal amt;
    ExpenseShare expenseShare;

    public Transaction(User from, User to, LocalDateTime madeOn, BigDecimal amt, ExpenseShare expenseShare) {
        this.from = from;
        this.to = to;
        this.madeOn = madeOn;
        this.amt = amt;
        this.expenseShare = expenseShare;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + from +
                ", to=" + to +
                ", madeOn=" + madeOn +
                ", amt=" + amt +
                ", expenseShare=[Id=" + expenseShare.getExpenseShareId()+" ,expense share="+ expenseShare.getTotalShare() +"]"+
                ", expense=[Id=" + expenseShare.getExpense().getExpenseId()+" ,total expense="+ expenseShare.getExpense().getTotal() +"]"+
                '}';
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public LocalDateTime getMadeOn() {
        return madeOn;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public ExpenseShare getExpenseShare() {
        return expenseShare;
    }
}
