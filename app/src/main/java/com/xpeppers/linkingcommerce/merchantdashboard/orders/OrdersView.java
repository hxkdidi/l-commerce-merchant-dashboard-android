package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import java.util.List;

public interface OrdersView extends LoadableView {

    void showError();

    void show(List<Order> orders);

    void loaded();
}
