package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import java.util.List;

public interface OrdersView {
    void showError();
    void show(OrdersAdapter adapter, List<Order> orders);
}
