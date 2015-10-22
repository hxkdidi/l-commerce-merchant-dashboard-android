package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import java.util.List;

public interface OrdersView {

    void showError();

    void show(List<Order> orders);

    void startSpinner();

    void stopSpinner();
}
