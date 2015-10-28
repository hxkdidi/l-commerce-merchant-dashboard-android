package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersPresenter implements Callback<List<Order>> {

    private final OrderService service;
    private final OrdersView view;
    private List<Order> orders;

    public OrdersPresenter(OrdersView view, OrderService service) {
        this.service = service;
        this.view = view;
    }

    public void render() {
        view.loading();
        service.fetchMerchantOrders(this);
    }

    public void showBasicFilteredOrders() {
        List<Order> filteredOrders = new ArrayList<>();
        for(Order order : orders) {
            if(order.getStatus().equals(OrderStatus.USED.asString()) || order.getStatus().equals(OrderStatus.UNUSED.asString())) {
                filteredOrders.add(order);
            }
        }
        view.show(filteredOrders);
        view.loaded();
    }

    public void showWithFilteredStatus(String statusFilter) {
        List<Order> filteredOrders = new ArrayList<>();
        for(Order order : orders) {
            if(order.getStatus().equals(statusFilter)) {
                filteredOrders.add(order);
            }
        }
        view.show(filteredOrders);
    }

    @Override
    public void success(List<Order> orders, Response response) {
        this.orders = orders;
        showBasicFilteredOrders();
    }

    @Override
    public void failure(RetrofitError error) {
        view.loaded();
        view.showError();
    }
}
