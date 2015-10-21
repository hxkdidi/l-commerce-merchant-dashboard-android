package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrdersPresenterTest {

    @Mock private OrderService service;
    @Mock private OrdersAdapter adapter;

    private OrdersPresenter presenter;

    @Test
    public void retrieves_the_orders_from_orders_service() {
        presenter = new OrdersPresenter(service, adapter);

        presenter.showOrders();

        verify(service).getOrders(any(Callback.class));
    }

}
