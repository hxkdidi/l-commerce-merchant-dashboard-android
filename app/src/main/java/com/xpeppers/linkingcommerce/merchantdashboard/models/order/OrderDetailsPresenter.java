package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

public class OrderDetailsPresenter {
    private OrderDetailsView view;

    public OrderDetailsPresenter(OrderDetailsView view) {
        this.view = view;
    }

    public void show(Order order) {
        view.showBuyerEmail(order.getBuyerEmail());
        view.showTitle(order.getTitle());
        view.showPurchaseDate(convertISO8601Date(order.getDate()));
        if (order.getCoupon() != null) {
            String couponCode = order.getCoupon().getCode();
            view.showCouponCode(convertCouponCode(couponCode));
        }
        if (order.getStatus() != null)
            view.showOrderStatus(OrderStatusConverter.orderStatusFrom(order.getStatus()));
    }

    private String convertCouponCode(String couponCode) {
        return couponCode == null ? "******" : "**" + couponCode.substring(2);
    }

    private String convertISO8601Date(final String dateString) {
        try {
            String[] splitISO8601String = dateString.split("T");

            String[] splitDate = splitISO8601String[0].split("-");
            String convertedDate = splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];

            String[] splitTime = splitISO8601String[1].split(":");
            String convertTime = splitTime[0] + ":" + splitTime[1];

            return convertedDate + " " + convertTime;
        } catch (Exception ex) {
            return "";
        }
    }
}
