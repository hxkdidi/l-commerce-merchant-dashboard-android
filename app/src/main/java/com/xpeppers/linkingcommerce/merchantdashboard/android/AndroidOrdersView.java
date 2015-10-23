package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrdersView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.MessageAlert;

import java.util.List;

public class AndroidOrdersView implements OrdersView {

    private Context context;
    private OrdersAdapter adapter;
    private ListView orderListView;
    private TextView noOrdersView;
    private View progressView;
    private MessageAlert alert;

    public AndroidOrdersView(final OrdersListActivity activity) {
        activity.setContentView(R.layout.activity_orders_list);

        this.orderListView = (ListView) activity.findViewById(R.id.offers_list_view);
        this.noOrdersView = (TextView) activity.findViewById(R.id.no_offers_textview);
        this.adapter = new OrdersAdapter(activity);

        this.context = activity;
        this.alert = new AndroidMessageAlert(activity);
        this.progressView = activity.findViewById(R.id.offers_progress_bar);

        orderListView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        alert.show(context.getString(R.string.generic_error), context.getString(R.string.generic_error_message));
    }

    @Override
    public void show(List<Order> orders) {
        if (orders.isEmpty()) {
            noOrdersView.setVisibility(View.VISIBLE);
        } else {
            orderListView.setVisibility(View.VISIBLE);
            adapter.setOrders(orders);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loading() {
        progressView.setVisibility(View.VISIBLE);
        orderListView.setVisibility(View.GONE);
        noOrdersView.setVisibility(View.GONE);
    }

    @Override
    public void loaded() {
        progressView.setVisibility(View.GONE);
    }

}
