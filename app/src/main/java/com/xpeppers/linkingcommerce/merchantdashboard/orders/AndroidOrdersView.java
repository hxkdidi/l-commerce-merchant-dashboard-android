package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.content.Context;
import android.view.View;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.signin.MessageAlert;

import java.util.List;

public class AndroidOrdersView implements OrdersView {

    private Context context;
    private OrdersAdapter adapter;
    private MessageAlert alert;
    private View progressView;

    public AndroidOrdersView(Context context, OrdersAdapter adapter, MessageAlert alert, View progressView) {
        this.context = context;
        this.adapter = adapter;
        this.alert = alert;
        this.progressView = progressView;
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

    @Override
    public void startSpinner() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopSpinner() {
        progressView.setVisibility(View.GONE);
    }

}
