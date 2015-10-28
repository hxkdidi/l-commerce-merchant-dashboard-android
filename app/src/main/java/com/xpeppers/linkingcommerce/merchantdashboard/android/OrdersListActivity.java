package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForOrders;

public class OrdersListActivity extends AppCompatActivity {

    private OrdersPresenter presenter;
    private Menu menu;
    private boolean menuIsCreated = false;

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
        resetMenuFilterCheck(menu);
        presenter.render();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        menuIsCreated = true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_list_menu, menu);
        resetMenuFilterCheck(menu);
        return true;
    }

    private void resetMenuFilterCheck(Menu menu) {
        if(menuIsCreated)
            menu.getItem(0).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all:
                presenter.render();
                item.setChecked(true);
                return true;
            case R.id.used:
                presenter.showWithFilteredStatus(OrderStatus.USED.asString());
                item.setChecked(true);
                return true;
            case R.id.unused:
                presenter.showWithFilteredStatus(OrderStatus.UNUSED.asString());
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
