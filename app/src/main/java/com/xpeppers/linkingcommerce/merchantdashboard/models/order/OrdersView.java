package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import com.xpeppers.linkingcommerce.merchantdashboard.models.LoadableView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;

import java.util.List;

public interface OrdersView extends LoadableView {

    void showError();

    void show(List<Order> orders);

    void loaded();
}
