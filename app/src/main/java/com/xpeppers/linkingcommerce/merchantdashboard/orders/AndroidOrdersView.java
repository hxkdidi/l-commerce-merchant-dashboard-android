package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.content.Context;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.signin.MessageAlert;

import java.util.List;

public class AndroidOrdersView implements OrdersView {

    private OrdersAdapter adapter;
    private MessageAlert alert;
    private Context context;

    public AndroidOrdersView(Context context, OrdersAdapter adapter, MessageAlert alert) {
        this.context = context;
        this.adapter = adapter;
        this.alert = alert;
    }

    @Override
    public void showError() {
        alert.showMessage(context.getString(R.string.generic_error), context.getString(R.string.generic_error_message));
    }

    @Override
    public void show(List<Order> orders) {
        adapter.setOrders(orders);
        adapter.notifyDataSetChanged();
    }

}
