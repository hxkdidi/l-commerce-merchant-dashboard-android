package com.xpeppers.linkingcommerce.merchantdashboard.orderDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.Order;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Order order = getOrder();
        Log.d("status ", order.getCoupon().getCode());
    }

    public Order getOrder() {
        return getIntent().getParcelableExtra("ORDER");
    }
}
