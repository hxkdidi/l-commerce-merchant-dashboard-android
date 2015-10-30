package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.Activity;
import android.content.Context;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatusConverter;

import java.util.Map;
import java.util.TreeMap;

public class OrderStatusHelper {

    public static Map<OrderStatus, Integer> ORDER_STATUS_MESSAGE = new TreeMap();

    static {
        ORDER_STATUS_MESSAGE.put(OrderStatus.USED, R.string.used_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.UNUSED, R.string.unused_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.EXPIRED, R.string.expired_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.CANCELED, R.string.canceled_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.UNKNOW, R.string.unknow_status);
    }

    private Context context;

    public OrderStatusHelper(Context context) {
        this.context = context;
    }

    public String getOrderStatus(OrderStatus orderStatus) {
        return context.getString(ORDER_STATUS_MESSAGE.get(orderStatus));
    }

    public int getOrderStatusIndicatorColor(Activity activity, String orderStatus) {
        return activity.getResources().getColor(orderStatus.equals(OrderStatus.UNUSED.asString()) ?
                R.color.light_orange : R.color.green);
    }
}
