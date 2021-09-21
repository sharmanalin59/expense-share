package com.udaan.expensemgmt.server.entity.currency;

import java.math.BigDecimal;

/**
 * @author nalin.sharma on 08/09/21
 */
public class CurrencyConversionUtil {

    private static BigDecimal oneInrToUsd = new BigDecimal("73");
    private static BigDecimal oneInrToPound = new BigDecimal("100");

    public BigDecimal convertFromInrToUSD(BigDecimal inr) {
        return inr.multiply(oneInrToUsd);
    }

    public BigDecimal convertToPound(BigDecimal inr) {
        return inr.multiply(oneInrToPound);
    }
}
