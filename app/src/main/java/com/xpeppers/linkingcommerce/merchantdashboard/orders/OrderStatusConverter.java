package com.xpeppers.linkingcommerce.merchantdashboard.orders;

public class OrderStatusConverter {

    public static OrderStatus orderStatusFrom(String statusString){
        try{
            return OrderStatus.valueOf(statusString.toUpperCase());
        }catch (Exception ex){
            return OrderStatus.UNKNOW;
        }
    }
}
