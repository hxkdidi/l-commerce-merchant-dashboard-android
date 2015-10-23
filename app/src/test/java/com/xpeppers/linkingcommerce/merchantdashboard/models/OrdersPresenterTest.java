package com.xpeppers.linkingcommerce.merchantdashboard.models;

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
    public void retrieves_the_sold_offers() {
        presenter = new OrdersPresenter(view, service);

        presenter.render();

        verify(service).fetchMerchantOrders(any(Callback.class));
        verify(view).loading();
    }

    @Test
    public void on_failure_it_shows_errors() {
        presenter = new OrdersPresenter(view, service);

        presenter.failure(null);

        verify(view).showError();
        verify(view).loaded();
    }

    @Test
    public void on_success_it_shows_orders() {
        presenter = new OrdersPresenter(view, service);

        presenter.success(null, null);

        verify(view).show(anyListOf(Order.class));
        verify(view).loaded();
    }

}
