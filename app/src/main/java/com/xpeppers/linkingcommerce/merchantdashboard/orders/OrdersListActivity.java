package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;

public class OrdersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders_list);
        ListView view = (ListView) findViewById(R.id.offers_list_view);

        OrdersAdapter adapter = new OrdersAdapter(this);
        view.setAdapter(adapter);

        OrderService service = ServiceFactory.createForOrders(sessionToken());

        OrdersPresenter presenter = new OrdersPresenter(service, adapter);
        presenter.showOrders();
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }
}
