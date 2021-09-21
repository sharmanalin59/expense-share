package com.udaan.expensemgmt.server.entity;

import com.udaan.expensemgmt.server.compute.BaseComputeStrategy;
import com.udaan.expensemgmt.server.compute.PercentageBasedComputeStrategy;
import com.udaan.expensemgmt.server.compute.RealShareBasedComputeStrategy;
import com.udaan.expensemgmt.server.compute.Strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nalin.sharma on 07/09/21
 */
public class Expense {
    private int expenseId;
    private BigDecimal total;
    private BigDecimal paid;
    private BaseComputeStrategy computeStrategy;
    private String currency;
    private User createdBy;
    private Map<Integer, ExpenseShare> expenseShareMap;
    private boolean deleted;

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", total=" + total +
                ", paid=" + paid +
                ", computeStrategy=" + computeStrategy +
                ", currency='" + currency + '\'' +
                ", createdBy=" + createdBy +
                ", userExpenseShareMap=" + expenseShareMap +
                ", deleted=" + deleted +
                '}';
    }

    public Expense(BigDecimal total, User createdBy, String currency, Strategy strategy) {
        this.total = total;
        this.createdBy = createdBy;
        this.currency = currency;
        this.deleted = false;
        this.expenseShareMap = new HashMap<>();
        if(strategy == Strategy.real) {
            this.computeStrategy = new RealShareBasedComputeStrategy();
        }
        else {
            this.computeStrategy = new PercentageBasedComputeStrategy();
        }
    }

    public void addToExpenseShareMap(ExpenseShare expenseShare) {
        this.expenseShareMap.put(expenseShare.getExpenseShareId(), expenseShare);
    }

    /*public void setComputeStrategy(BaseComputeStrategy computeStrategy) {
        this.computeStrategy = computeStrategy;
    }*/

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BaseComputeStrategy getComputeStrategy() {
        return computeStrategy;
    }

    public String getCurrency() {
        return currency;
    }

    public Map<Integer, ExpenseShare> getExpenseShareMap() {
        return expenseShareMap;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
