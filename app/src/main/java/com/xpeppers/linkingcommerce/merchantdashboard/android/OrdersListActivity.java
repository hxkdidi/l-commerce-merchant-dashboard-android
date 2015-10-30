package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderFilter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.StatusOrderFilter;

import java.lang.reflect.Field;
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

    private Menu menu;
    private OrdersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        forceMenuShownInActionBar();

        OrdersView view = new AndroidOrdersView(this);

        OrderService service = createForOrders(sessionToken());
        presenter = new OrdersPresenter(view, service);
    }

    private void forceMenuShownInActionBar() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.render();
    }

    @Override
    protected void onPause() {
        super.onPause();

        resetMenuFilterCheck();
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
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_list_menu, menu);
        resetMenuFilterCheck();
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

    private void resetMenuFilterCheck() {
        menu.findItem(R.id.all).setChecked(true);
    }

}
