package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.OrdersView;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForOrders;

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
