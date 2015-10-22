package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrdersPresenterTest {

    @Mock private OrderService service;
    @Mock private OrdersView view;

    private OrdersPresenter presenter;

    @Test
    public void retrieves_the_orders() {
        presenter = new OrdersPresenter(service, view);

        presenter.showOrders();

        verify(service).getOrders(any(Callback.class));
        verify(view).startSpinner();
    }

    @Test
    public void on_failure_it_shows_errors() {
        presenter = new OrdersPresenter(service, view);

        presenter.failure(null);

        verify(view).showError();
        verify(view).stopSpinner();
    }

    @Test
    public void on_success_it_shows_orders() {
        presenter = new OrdersPresenter(service, view);

        presenter.success(null, null);

        verify(view).show(anyListOf(Order.class));
        verify(view).stopSpinner();
    }

}
