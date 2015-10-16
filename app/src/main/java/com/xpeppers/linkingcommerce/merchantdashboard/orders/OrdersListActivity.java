package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersListActivity extends AppCompatActivity implements Callback<List<Order>> {

    private static final String BACKOFFICE_URL = "http://private-46e03-linkingcommerceapi.apiary-mock.com/api";
    private ListView ordersListView;
    private OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        ordersListView = (ListView) findViewById(R.id.offers_list_view);
        ordersAdapter = new OrdersAdapter(this);
        ordersListView.setAdapter(ordersAdapter);

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BACKOFFICE_URL)
                .build();

        Intent intent = getIntent();
        String token = intent.getStringExtra("TOKEN");

        OrderService orderService = adapter.create(OrderService.class);
        orderService.getOrders("token="+token, this);
    }

    @Override
    public void success(List<Order> orders, Response response) {
        ordersAdapter.setOrders(orders);
        ordersAdapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("orders size", "error "+error.getMessage());
    }
}
