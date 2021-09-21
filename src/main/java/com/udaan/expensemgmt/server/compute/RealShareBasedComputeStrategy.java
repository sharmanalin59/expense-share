package com.udaan.expensemgmt.server.compute;

/**
 * @author nalin.sharma on 08/09/21
 */
public class RealShareBasedComputeStrategy extends BaseComputeStrategy {
    public double compute(double amt, double factor) {
        return amt * 1;
    }
}
