package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

public interface OrderDetailsView {
    void showBuyerEmail(String email);

    void showTitle(String title);

    void showPurchaseDate(String purchaseDate);

    void showCouponCode(String code);

    void showOrderStatus(OrderStatus orderStatus);
}
