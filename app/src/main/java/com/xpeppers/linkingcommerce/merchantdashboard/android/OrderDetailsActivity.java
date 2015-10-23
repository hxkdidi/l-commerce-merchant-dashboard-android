package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsPresenter;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView emailField = (TextView) findViewById(R.id.buyer_field_text_view);
        TextView titleField = (TextView) findViewById(R.id.order_title_text_view);
        TextView purchaseDateField = (TextView) findViewById(R.id.order_purchase_date_text_view);
        TextView couponCodeField = (TextView) findViewById(R.id.coupon_code_text_view);
        TextView orderStatusField = (TextView) findViewById(R.id.order_state_text_view);

        AndroidOrderDetailsView view = new AndroidOrderDetailsView(this, emailField, titleField, purchaseDateField, couponCodeField, orderStatusField);

        OrderDetailsPresenter presenter = new OrderDetailsPresenter(view);
        presenter.show(getOrder());
    }

    public Order getOrder() {
        return getIntent().getParcelableExtra("ORDER");
    }
}
