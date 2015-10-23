package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.test.AndroidTestCase;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class AndroidOrderDetailsViewTest extends AndroidTestCase {

    private AndroidOrderDetailsView view;
    private TextView emailField;
    private TextView titleField;
    private TextView purchaseDateField;
    private TextView couponCodeField;
    private TextView orderStatusField;

    public void setUp() {
        emailField = new TextView(getContext());
        titleField = new TextView(getContext());
        purchaseDateField = new TextView(getContext());
        couponCodeField = new TextView(getContext());
        orderStatusField = new TextView(getContext());
        view = new AndroidOrderDetailsView(getContext(), emailField, titleField, purchaseDateField, couponCodeField, orderStatusField);
    }

    public void
    testShowBuyerEmail() throws Exception {
        view.showBuyerEmail("dev@xpeppers.com");

        assertEquals("dev@xpeppers.com", emailField.getText().toString());
    }

    public void
    testShowTitle() throws Exception {
        view.showTitle("Titolo offerta");

        assertEquals("Titolo offerta", titleField.getText().toString());
    }

    public void
    testShowPurchaseDate() throws Exception {
        view.showPurchaseDate("23/10/2015 10:12");

        assertEquals("23/10/2015 10:12", purchaseDateField.getText().toString());
    }

    public void
    testShowCouponCode() throws Exception {
        view.showCouponCode("xx3456");

        assertEquals("xx3456", couponCodeField.getText().toString());
    }

    public void
    testShowOrderStatus() throws Exception {
        view.showOrderStatus(OrderStatus.USED);

        OrderStatusHelper orderStatusHelper = new OrderStatusHelper(getContext());
        assertEquals(orderStatusHelper.getOrderStatus(OrderStatus.USED), orderStatusField.getText().toString());
    }
}
