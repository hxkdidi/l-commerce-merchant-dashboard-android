package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderFilter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.StatusOrderFilter;

import java.util.HashMap;
import java.util.Map;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForOrders;

public class OrdersListActivity extends AppCompatActivity {

    private final static Map<Integer, OrderFilter> FILTERS_MAP = new HashMap<>();

    static {
        FILTERS_MAP.put(R.id.all, OrdersPresenter.DEFAULT);
        FILTERS_MAP.put(R.id.used, new StatusOrderFilter(OrderStatus.USED.asString()));
        FILTERS_MAP.put(R.id.unused, new StatusOrderFilter(OrderStatus.UNUSED.asString()));
    }

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_list_menu, menu);
        menu.findItem(R.id.all).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        OrderFilter orderFilter = FILTERS_MAP.get(item.getItemId());
        presenter.showOrdersFilteredBy(orderFilter);

        return true;
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }

}
