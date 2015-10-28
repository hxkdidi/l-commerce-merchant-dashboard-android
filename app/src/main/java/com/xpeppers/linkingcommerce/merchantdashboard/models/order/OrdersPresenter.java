package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersPresenter implements Callback<List<Order>> {

    public final static OrOrderFilter DEFAULT = new OrOrderFilter();

    static {
        DEFAULT.add(new StatusOrderFilter(OrderStatus.USED.asString()));
        DEFAULT.add(new StatusOrderFilter(OrderStatus.UNUSED.asString()));
    }


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


    public void showOrdersFilteredBy(OrderFilter filter){
        List<Order> filteredOrders = new ArrayList<>();

        for(Order order : orders) {
            if(filter.doFilter(order)) {
                filteredOrders.add(order);
            }
        }

        view.show(filteredOrders);
        view.loaded();
    }

    @Override
    public void success(List<Order> orders, Response response) {
        this.orders = orders;
        showOrdersFilteredBy(DEFAULT);
    }

    @Override
    public void failure(RetrofitError error) {
        view.loaded();
        view.showError();
    }
}
