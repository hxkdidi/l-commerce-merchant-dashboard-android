package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderStatus;

public class OrderStatusConverter {

    public static OrderStatus orderStatusFrom(String statusString){
        try{
            return OrderStatus.valueOf(statusString.toUpperCase());
        }catch (Exception ex){
            return OrderStatus.UNKNOW;
        }
    }
}
