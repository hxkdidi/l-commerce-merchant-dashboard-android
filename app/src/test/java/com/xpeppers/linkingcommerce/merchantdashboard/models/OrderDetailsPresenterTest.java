package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Coupon;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsPresenterTest {

    private static final String BUYER_EMAIL = "dev@xpeppers.com";
    private static final String TITLE = "luganica";
    public static final String A_DATE = "2015-09-28T13:55:57Z";
    public static final String A_COUPON_CODE = "123456";

    @Mock
    private OrderDetailsView view;
    private OrderDetailsPresenter presenter;
    private Order order;

    @Before
    public void setSetup() {
        presenter = new OrderDetailsPresenter(view);
    }

    @Test
    public void shows_buyer_email() {
        order = new Order();
        order.setBuyerEmail(BUYER_EMAIL);

        presenter.show(order);

        verify(view).showBuyerEmail(eq(BUYER_EMAIL));
    }

    @Test
    public void shows_order_title() {
        order = new Order();
        order.setTitle(TITLE);

        presenter.show(order);

        verify(view).showTitle(eq(TITLE));
    }

    @Test
    public void shows_purchase_date() {
        order = new Order();
        order.setDate(A_DATE);

        presenter.show(order);

        verify(view).showPurchaseDate(eq("28/09/2015 13:55"));
    }

    @Test
    public void shows_coupon_code() {
        order = new Order();
        Coupon coupon = new Coupon(A_COUPON_CODE);
        order.setCoupon(coupon);

        presenter.show(order);

        verify(view).showCouponCode(eq("123456"));
    }

    @Test
    public void shows_order_status_used() {
        order = new Order();
        order.setStatus("used");

        presenter.show(order);

        verify(view).showOrderStatus(eq(OrderStatus.USED));
    }

}
