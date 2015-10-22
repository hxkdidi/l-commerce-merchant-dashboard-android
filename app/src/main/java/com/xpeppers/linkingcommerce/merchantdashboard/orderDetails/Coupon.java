package com.xpeppers.linkingcommerce.merchantdashboard.orderDetails;

public class Coupon {

    private String code;

    public Coupon(){

    }

    public Coupon(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
