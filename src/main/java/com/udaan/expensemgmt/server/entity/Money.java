package com.udaan.expensemgmt.server.entity;

import java.math.BigDecimal;

/**
 * @author nalin.sharma on 08/09/21
 */
public class Money {
    private BigDecimal amt;
    private String currency;

    public Money(BigDecimal amt, String currency) {
        this.amt = amt;
        this.currency = currency;
    }
}
