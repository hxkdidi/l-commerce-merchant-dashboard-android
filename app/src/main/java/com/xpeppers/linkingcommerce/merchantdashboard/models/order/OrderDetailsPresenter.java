package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import android.util.Log;

import com.xpeppers.linkingcommerce.merchantdashboard.utils.*;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrderDetailsPresenter implements Callback<StatusUpdateResponse> {
    private OrderDetailsView view;
    private OrderService service;

    public OrderDetailsPresenter(OrderDetailsView view, OrderService service) {
        this.view = view;
        this.service = service;
    }

    public void show(Order order) {
        view.showBuyerEmail(order.getBuyerEmail());
        view.showTitle(order.getTitle());
        view.showPurchaseDate(DateUtils.convertFromISO8601(order.getDate()));

        if (order.getCoupon() != null) {
            view.showCouponCode(order.getCouponCode());
        }

        if (order.getStatus() != null)
            view.showOrderStatus(OrderStatusConverter.orderStatusFrom(order.getStatus()));
    }

    public void changeStatus(int id, OrderStatus status) {
        view.loading();
        StatusUpdateBody body = new StatusUpdateBody();
        body.setStatus(status.asString());
        service.changeOrderStatus(id, body, this);
    }

    @Override
    public void success(StatusUpdateResponse statusUpdateResponse, Response response) {
        view.loaded();
    }

    @Override
    public void failure(RetrofitError error) {
        view.loaded();
        view.showError();
    }
}

