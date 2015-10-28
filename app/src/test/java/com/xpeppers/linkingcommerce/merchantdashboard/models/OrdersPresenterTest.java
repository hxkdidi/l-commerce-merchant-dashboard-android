package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrdersPresenterTest {

    @Mock private OrderService service;
    @Mock private OrdersView view;

    private OrdersPresenter presenter;

    @Before
    public void setup() {
        presenter = new OrdersPresenter(view, service);
    }

    @Test
    public void retrieves_the_sold_offers() {
        presenter.render();

        verify(service).fetchMerchantOrders(any(Callback.class));
        verify(view).loading();
    }

    @Test
    public void on_failure_it_shows_errors() {
        presenter.failure(null);

        verify(view).showError();
        verify(view).loaded();
    }

    @Test
    public void on_success_it_shows_orders() {
        presenter.success(new ArrayList<Order>(), null);

        verify(view).show(anyListOf(Order.class));
        verify(view).loaded();
    }

    @Test
    public void show_only_used_and_unused_orders() {
        List<Order> orders = new ArrayList<Order>();
        Order usedOrder = new Order();
        usedOrder.setStatus(OrderStatus.USED.asString());
        Order unusedOrder = new Order();
        unusedOrder.setStatus(OrderStatus.UNUSED.asString());
        Order unknownOrder = new Order();
        unknownOrder.setStatus(OrderStatus.UNKNOW.asString());
        Order canceledOrder = new Order();
        canceledOrder.setStatus(OrderStatus.CANCELED.asString());

        orders.add(usedOrder);
        orders.add(unusedOrder);
        orders.add(unknownOrder);
        orders.add(canceledOrder);

        presenter.success(orders, null);

        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(usedOrder);
        expectedOrders.add(unusedOrder);

        verify(view).show(eq(expectedOrders));
    }

    @Test
    public void with_filter_used_it_shows_only_used_orders() {

        List<Order> orders = new ArrayList<Order>();
        Order usedOrder = new Order();
        usedOrder.setStatus(OrderStatus.USED.asString());
        Order unusedOrder = new Order();
        unusedOrder.setStatus(OrderStatus.UNUSED.asString());

        orders.add(usedOrder);
        orders.add(unusedOrder);

        presenter.success(orders, null);

        presenter.showWithFilteredStatus(OrderStatus.USED.asString());

        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(usedOrder);

        verify(view).show(eq(expectedOrders));

    }

}
