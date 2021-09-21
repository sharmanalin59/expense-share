package com.udaan.expensemgmt.server.compute;

import java.math.BigDecimal;

/**
 * @author nalin.sharma on 08/09/21
 */
public abstract class BaseComputeStrategy {
    public BigDecimal compute(BigDecimal amt, double factor) {
        return amt.multiply(1);
    }
}
