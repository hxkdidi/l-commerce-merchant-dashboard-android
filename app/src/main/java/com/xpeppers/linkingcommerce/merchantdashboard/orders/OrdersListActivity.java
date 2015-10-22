package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.signin.MessageAlert;

public class OrdersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders_list);

        ListView listView = (ListView) findViewById(R.id.offers_list_view);

        OrdersAdapter adapter = new OrdersAdapter(this);
        listView.setAdapter(adapter);

        OrderService service = ServiceFactory.createForOrders(sessionToken());

        MessageAlert alert = new MessageAlert() {
            @Override
            public void showMessage(String title, String message) {
                Toast.makeText(OrdersListActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };

        View progressView = findViewById(R.id.offers_progress_bar);

        OrdersView ordersView = new AndroidOrdersView(this, adapter, alert, progressView);

        OrdersPresenter presenter = new OrdersPresenter(service, ordersView);
        presenter.showOrders();
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }
}
