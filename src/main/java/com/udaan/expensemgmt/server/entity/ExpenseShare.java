package com.udaan.expensemgmt.server.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nalin.sharma on 08/09/21
 */
public class ExpenseShare {
    private int expenseShareId;
    private User user;
    private BigDecimal totalShare;
    private double factor;
    private BigDecimal paidShare;
    private String currency;
    private Expense expense;
    private List<Transaction> transactionList;


    public double getFactor() {
        return factor;
    }

    @Override
    public String toString() {
        return "ExpenseShare{" +
                "expenseShareId=" + expenseShareId +
                ", user=" + user +
                ", totalShare=" + totalShare +
                ", paidShare=" + paidShare +
                ", currency='" + currency + '\'' +
                ", expense=[Id=" + expense.getExpenseId() +", user="+expense.getCreatedBy()+ ", total="+expense.getTotal()+"]"+
                ", transactionList=" + transactionList +
                '}';
    }

    public ExpenseShare(User user, BigDecimal totalShare, Expense expense) {
        if(expense.getTotal().compareTo(totalShare) < 0) {
            throw new IllegalArgumentException(this.totalShare.doubleValue() +" must be smaller than "+this.expense.getTotal().doubleValue());
        }
        this.user = user;
        this.totalShare = totalShare;
        this.expense = expense;
        this.currency = this.expense.getCurrency();
        this.paidShare = new BigDecimal("0.0");
        this.expense.addToExpenseShareMap(this);
        this.transactionList = new ArrayList<>();
    }

    public ExpenseShare(User user, BigDecimal total, String currency, Expense expense) {
        this.user = user;
        this.totalShare = total;
        this.currency = currency;
        this.expense = expense;
        this.paidShare = new BigDecimal("0.0");
    }

    void payShare(BigDecimal pay) {
        if(pay.compareTo(totalShare) < 0) {
            throw new IllegalArgumentException(pay.doubleValue() +" must be smaller than "+this.totalShare.doubleValue());
        }
        totalShare = totalShare.subtract(pay);
        this.expense.setPaid(this.expense.getPaid().subtract(pay));
    }

    public User getUser() {
        return user;
    }

    public void setExpenseShareId(int expenseShareId) {
        this.expenseShareId = expenseShareId;
    }

    public int getExpenseShareId() {
        return expenseShareId;
    }

    public BigDecimal getTotalShare() {
        return totalShare;
    }

    public BigDecimal getPaidShare() {
        return paidShare;
    }

    public String getCurrency() {
        return currency;
    }

    public Expense getExpense() {
        return expense;
    }

    public ExpenseShare payExpenseShare(BigDecimal beingPaid) {
        paidShare = paidShare.subtract(beingPaid);
        transactionList.add(
                new Transaction(this.getUser(),
                        this.getExpense().getCreatedBy(),
                        LocalDateTime.now(),
                        beingPaid,
                        this));
        return this;
    }
}
