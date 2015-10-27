package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForOrders;

public class OrdersListActivity extends AppCompatActivity {


    private OrdersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OrdersView view = new AndroidOrdersView(this);

        OrderService service = createForOrders(sessionToken());
        presenter = new OrdersPresenter(view, service);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.render();
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }
}
