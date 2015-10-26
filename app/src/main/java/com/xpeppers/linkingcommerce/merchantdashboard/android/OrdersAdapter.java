package com.xpeppers.linkingcommerce.merchantdashboard.android;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatusConverter;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends BaseAdapter {

    private final OrdersListActivity ordersActivity;
    private List<Order> orders;

    private OrderStatusHelper orderStatusHelper;

    public OrdersAdapter(OrdersListActivity ordersActivity){
        this.ordersActivity = ordersActivity;
        orderStatusHelper = new OrderStatusHelper(ordersActivity);
        orders = new ArrayList();
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = ordersActivity.getLayoutInflater();
        View orderItem = inflater.inflate(R.layout.order_item, parent, false);
        Order order = orders.get(i);
        TextView buyerEmail = (TextView) orderItem.findViewById(R.id.buyer_email_text_view);
        TextView offerTitle = (TextView) orderItem.findViewById(R.id.offer_title_text_view);
        TextView status = (TextView) orderItem.findViewById(R.id.status_text_view);
        buyerEmail.setText(order.getBuyerEmail());
        offerTitle.setText(order.getTitle());
        OrderStatus orderStatus = OrderStatusConverter.orderStatusFrom(order.getStatus());
        status.setText(orderStatusHelper.getOrderStatus(orderStatus));
        orderItem.setOnClickListener(new OrderItemClickListener(ordersActivity, order));
        return orderItem;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private class OrderItemClickListener implements View.OnClickListener {
        private OrdersListActivity ordersActivity;
        private Order order;

        public OrderItemClickListener(OrdersListActivity ordersActivity, Order order) {
            this.ordersActivity = ordersActivity;
            this.order = order;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ordersActivity, OrderDetailsActivity.class);
            intent.putExtra("ORDER", new OrderParcelable(order));
            intent.putExtra("TOKEN", sessionToken());
            ordersActivity.startActivity(intent);
        }
    }

    private String sessionToken() {
        return ordersActivity.getIntent().getStringExtra("TOKEN");
    }
}
