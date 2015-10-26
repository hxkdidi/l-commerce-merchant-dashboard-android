package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import com.xpeppers.linkingcommerce.merchantdashboard.utils.*;

public class OrderDetailsPresenter {
    private OrderDetailsView view;

    public OrderDetailsPresenter(OrderDetailsView view) {
        this.view = view;
    }

    public void show(Order order) {
        view.showBuyerEmail(order.getBuyerEmail());
        view.showTitle(order.getTitle());
        view.showPurchaseDate(DateUtils.convertFromISO8601(order.getDate()));

        if (order.getCoupon() != null) {
            String couponCode = order.getCouponCode();
            view.showCouponCode(couponCode);
        }

        if (order.getStatus() != null)
            view.showOrderStatus(OrderStatusConverter.orderStatusFrom(order.getStatus()));
    }
}
