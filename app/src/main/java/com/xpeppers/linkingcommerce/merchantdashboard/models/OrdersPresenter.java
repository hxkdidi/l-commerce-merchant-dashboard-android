package com.xpeppers.linkingcommerce.merchantdashboard.models;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersPresenter implements Callback<List<Order>> {

    private final OrderService service;
    private final OrdersView view;

    public OrdersPresenter(OrdersView view, OrderService service) {
        this.service = service;
        this.view = view;
    }

    public void render() {
        view.loading();
        service.fetchMerchantOrders(this);
    }

    @Override
    public void success(List<Order> orders, Response response) {
        view.loaded();
        view.show(orders);
    }

    @Override
    public void failure(RetrofitError error) {
        view.loaded();
        view.showError();
    }
}
