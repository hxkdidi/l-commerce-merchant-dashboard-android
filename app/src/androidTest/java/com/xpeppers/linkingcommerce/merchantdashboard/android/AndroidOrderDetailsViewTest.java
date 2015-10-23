package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.test.AndroidTestCase;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class AndroidOrderDetailsViewTest extends AndroidTestCase {

    public void
    testShowBuyerEmail() throws Exception {
        TextView emailField = new TextView(getContext());
        AndroidOrderDetailsView orderDetailsView = new AndroidOrderDetailsView(getContext(), emailField, null, null, null, null);

        orderDetailsView.showBuyerEmail("dev@xpeppers.com");

        assertEquals("dev@xpeppers.com", emailField.getText().toString());
    }

    public void
    testShowTitle() throws Exception {
        TextView titleField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(getContext(), null, titleField, null, null, null);

        view.showTitle("Titolo offerta");

        assertEquals("Titolo offerta", titleField.getText().toString());
    }

    public void
    testShowPurchaseDate() throws Exception {
        TextView purchaseDateField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(getContext(), null, null, purchaseDateField, null, null);

        view.showPurchaseDate("23/10/2015 10:12");

        assertEquals("23/10/2015 10:12", purchaseDateField.getText().toString());
    }

    public void
    testShowCouponCode() throws Exception {
        TextView couponCodeField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(getContext(), null, null, null, couponCodeField, null);

        view.showCouponCode("xx3456");

        assertEquals("xx3456", couponCodeField.getText().toString());
    }

    public void
    testShowOrderStatus() throws Exception {
        TextView orderStatusField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(getContext(), null, null, null, null, orderStatusField);

        view.showOrderStatus(OrderStatus.USED);

        OrderStatusHelper orderStatusHelper = new OrderStatusHelper(getContext());

        assertEquals(orderStatusHelper.getOrderStatus(OrderStatus.USED), orderStatusField.getText().toString());

    }
}
