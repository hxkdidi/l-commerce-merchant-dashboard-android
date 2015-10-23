package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Context;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class AndroidOrderDetailsView implements OrderDetailsView {

    private TextView emailField;
    private TextView titleField;
    private TextView purchaseDateField;
    private TextView couponCodeField;
    private TextView orderStatusField;
    private OrderStatusHelper orderStatusHelper;

    public AndroidOrderDetailsView(Context context, TextView emailField, TextView titleField, TextView purchaseDateField, TextView couponCodeField, TextView orderStatusField) {
        this.emailField = emailField;
        this.titleField = titleField;
        this.purchaseDateField = purchaseDateField;
        this.couponCodeField = couponCodeField;
        this.orderStatusField = orderStatusField;
        orderStatusHelper = new OrderStatusHelper(context);
    }

    @Override
    public void showBuyerEmail(String email) {
        emailField.setText(email);
    }

    @Override
    public void showTitle(String title) {
        titleField.setText(title);
    }

    @Override
    public void showPurchaseDate(String purchaseDate) {
        purchaseDateField.setText(purchaseDate);
    }

    @Override
    public void showCouponCode(String code) {
        couponCodeField.setText(code);
    }

    @Override
    public void showOrderStatus(OrderStatus orderStatus) {
        orderStatusField.setText(orderStatusHelper.getOrderStatus(orderStatus));
    }
}
