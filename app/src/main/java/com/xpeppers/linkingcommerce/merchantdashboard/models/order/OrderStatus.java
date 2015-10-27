package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

public enum OrderStatus {
    USED,
    UNUSED,
    EXPIRED,
    CANCELED,
    UNKNOW;

    public String asString() {
        return toString().toLowerCase();
    }
}
