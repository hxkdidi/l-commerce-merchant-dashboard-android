package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.test.AndroidTestCase;
import android.widget.TextView;

public class AndroidOrderDetailsViewTest extends AndroidTestCase {

    public void
    testShowBuyerEmail() throws Exception {
        TextView emailField = new TextView(getContext());
        AndroidOrderDetailsView orderDetailsView = new AndroidOrderDetailsView(emailField, null, null, null);

        orderDetailsView.showBuyerEmail("dev@xpeppers.com");

        assertEquals("dev@xpeppers.com", emailField.getText().toString());
    }

    public void
    testShowTitle() throws Exception {
        TextView titleField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(null, titleField, null, null);

        view.showTitle("Titolo offerta");

        assertEquals("Titolo offerta", titleField.getText().toString());
    }

    public void
    testShowPurchaseDate() throws Exception {
        TextView purchaseDateField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(null, null, purchaseDateField, null);

        view.showPurchaseDate("23/10/2015 10:12");

        assertEquals("23/10/2015 10:12", purchaseDateField.getText().toString());
    }

    public void
    testShowCouponCode() throws Exception {
        TextView couponCodeField = new TextView(getContext());
        AndroidOrderDetailsView view = new AndroidOrderDetailsView(null, null, null, couponCodeField);

        view.showCouponCode("xx3456");

        assertEquals("xx3456", couponCodeField.getText().toString());
    }
}
