package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
    }

    public Order getOrder() {
        return getIntent().getParcelableExtra("ORDER");
    }
}
