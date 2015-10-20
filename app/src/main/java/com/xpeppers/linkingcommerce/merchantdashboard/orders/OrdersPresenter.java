package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.util.Log;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersPresenter implements Callback<List<Order>> {
    private String token;
    private final OrderService service;
    private final OrdersAdapter adapter;
    private final ListView view;

    public OrdersPresenter(String token, OrderService service, OrdersAdapter adapter, ListView view) {
        this.token = token;
        this.service = service;
        this.adapter = adapter;
        this.view = view;
    }

    public void showOrders() {
        service.getOrders("token=" + token, this);
    }

    @Override
    public void success(List<Order> orders, Response response) {
        adapter.setOrders(orders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("orders size", "error " + error.getMessage());
    }
}
