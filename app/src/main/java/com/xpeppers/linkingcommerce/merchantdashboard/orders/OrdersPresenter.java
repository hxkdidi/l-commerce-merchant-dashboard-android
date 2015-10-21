package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersPresenter implements Callback<List<Order>> {

    private final OrderService service;
    private final OrdersAdapter adapter;
    private final OrdersView view;

    public OrdersPresenter(OrderService service, OrdersAdapter adapter, OrdersView view) {
        this.service = service;
        this.adapter = adapter;
        this.view = view;
    }

    public void showOrders() {
        service.getOrders(this);
    }

    @Override
    public void success(List<Order> orders, Response response) {
        adapter.setOrders(orders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        view.showError();
    }
}
