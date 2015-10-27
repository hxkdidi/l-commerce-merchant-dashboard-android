package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import com.xpeppers.linkingcommerce.merchantdashboard.models.LoadableView;

public interface OrderDetailsView extends LoadableView {
    void showBuyerEmail(String email);

    void showTitle(String title);

    void showPurchaseDate(String purchaseDate);

    void showCouponCode(String code);

    void showOrderStatus(OrderStatus orderStatus);

    void showError();

    void loaded();
}
