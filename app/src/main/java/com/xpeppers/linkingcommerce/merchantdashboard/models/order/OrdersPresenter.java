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

    private void showBasicFilteredOrders() {
        OrderFilter usedOrderFilter = new StatusOrderFilter(OrderStatus.USED.asString());
        OrderFilter unUsedOrderFilter = new StatusOrderFilter(OrderStatus.UNUSED.asString());
        OrOrderFilter orOrderFilter = new OrOrderFilter();
        orOrderFilter.add(usedOrderFilter);
        orOrderFilter.add(unUsedOrderFilter);
        showOrdersFilteredBy(orOrderFilter);
    }


    private void showOrdersFilteredBy(OrderFilter filter){
        List<Order> filteredOrders = new ArrayList<>();

        for(Order order : orders) {
            if(filter.doFilter(order)) {
                filteredOrders.add(order);
            }
        }

        view.show(filteredOrders);
        view.loaded();
    }

    public void showWithFilteredStatus(String statusFilter) {
        showOrdersFilteredBy(new StatusOrderFilter(statusFilter));
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
