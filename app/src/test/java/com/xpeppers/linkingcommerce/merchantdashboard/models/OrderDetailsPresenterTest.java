package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Coupon;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.StatusUpdateBody;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.Callback;

import static com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus.USED;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsPresenterTest {

    private static final String BUYER_EMAIL = "dev@xpeppers.com";
    private static final String TITLE = "luganica";
    public static final String A_COUPON_CODE = "123456";
    public static final String ANY_DATE_AS_STRING = "2015-09-28T13:55:57Z";

    @Mock private OrderDetailsView view;
    @Mock private OrderService service;

    private OrderDetailsPresenter presenter;
    private Order order;

    @Before
    public void setup() {
        presenter = new OrderDetailsPresenter(view, service);
        order = new Order();
    }

    @Test
    public void shows_buyer_email() {
        order.setBuyerEmail(BUYER_EMAIL);

        presenter.show(order);

        verify(view).showBuyerEmail(eq(BUYER_EMAIL));
    }

    @Test
    public void shows_order_title() {
        order.setTitle(TITLE);

        presenter.show(order);

        verify(view).showTitle(eq(TITLE));
    }

    @Test
    public void shows_purchase_date() {
        order.setDate(ANY_DATE_AS_STRING);

        presenter.show(order);

        verify(view).showPurchaseDate(isA(String.class));
    }

    @Test
    public void shows_coupon_code() {
        Coupon coupon = new Coupon(A_COUPON_CODE);
        order.setCoupon(coupon);

        presenter.show(order);

        verify(view).showCouponCode(eq("123456"));
    }

    @Test
    public void shows_order_status_used() {
        order.setStatus("used");

        presenter.show(order);

        verify(view).showOrderStatus(eq(USED));
    }

    @Test
    public void changes_the_order_status() {
        presenter.changeStatus(1, USED);

        verify(service).changeOrderStatus(anyInt(), any(StatusUpdateBody.class), any(Callback.class));
    }

}
