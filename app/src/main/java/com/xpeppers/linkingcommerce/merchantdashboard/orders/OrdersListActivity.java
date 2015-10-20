package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.xpeppers.linkingcommerce.merchantdashboard.Constants;
import com.xpeppers.linkingcommerce.merchantdashboard.R;

import retrofit.RestAdapter;

public class OrdersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders_list);
        ListView view = (ListView) findViewById(R.id.offers_list_view);

        OrdersAdapter adapter = new OrdersAdapter(this);
        view.setAdapter(adapter);

        // Extract somewhere
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.BACKOFFICE_URL)
                .build();

        OrderService service = restAdapter.create(OrderService.class);

        Intent intent = getIntent();
        String token = intent.getStringExtra("TOKEN");

        OrdersPresenter presenter = new OrdersPresenter(token, service, adapter, view);
        presenter.showOrders();
    }
}
