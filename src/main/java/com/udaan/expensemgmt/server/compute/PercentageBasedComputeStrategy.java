package com.udaan.expensemgmt.server.compute;

import com.udaan.expensemgmt.server.compute.BaseComputeStrategy;

import java.math.BigDecimal;

/**
 * @author nalin.sharma on 08/09/21
 */
public class PercentageBasedComputeStrategy extends BaseComputeStrategy {
    public BigDecimal compute(BigDecimal amt, double factor) {
        return amt * factor / 100;
    }
}
