package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderDetailsView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderStatus;

public class AndroidOrderDetailsView implements OrderDetailsView {

    private TextView emailField;
    private TextView titleField;
    private TextView purchaseDateField;
    private TextView couponCodeField;

    public AndroidOrderDetailsView(TextView emailField, TextView titleField, TextView purchaseDateField, TextView couponCodeField) {
        this.emailField = emailField;
        this.titleField = titleField;
        this.purchaseDateField = purchaseDateField;
        this.couponCodeField = couponCodeField;
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

    }
}
