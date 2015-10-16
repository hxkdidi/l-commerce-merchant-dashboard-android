package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends BaseAdapter{

    private final OrdersListActivity ordersActivity;
    private List<Order> orders;

    public OrdersAdapter(OrdersListActivity ordersActivity){
        this.ordersActivity = ordersActivity;
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
        status.setText(order.getStatus());
        return orderItem;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
