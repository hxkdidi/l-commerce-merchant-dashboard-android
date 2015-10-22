package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.xpeppers.linkingcommerce.merchantdashboard.orders.ServiceFactory.createForOrders;

public class OrdersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OrdersView view = new AndroidOrdersView(this);

        OrderService service = createForOrders(sessionToken());
        OrdersPresenter presenter = new OrdersPresenter(view, service);

        presenter.render();
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }
}
