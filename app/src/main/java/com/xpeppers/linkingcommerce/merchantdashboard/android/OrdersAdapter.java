package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderStatus;
import com.xpeppers.linkingcommerce.merchantdashboard.models.OrderStatusConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrdersAdapter extends BaseAdapter{

    public static Map<OrderStatus, Integer> ORDER_STATUS_MESSAGE = new TreeMap();

    static {
        ORDER_STATUS_MESSAGE.put(OrderStatus.USED, R.string.used_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.UNUSED, R.string.unused_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.EXPIRED, R.string.expired_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.CANCELED, R.string.canceled_status);
        ORDER_STATUS_MESSAGE.put(OrderStatus.UNKNOW, R.string.unknow_status);
    }


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
        status.setText(getOrderStatus(order));
        return orderItem;
    }

    private String getOrderStatus(Order order) {
        OrderStatus orderStatus = OrderStatusConverter.orderStatusFrom(order.getStatus());
        return ordersActivity.getString(ORDER_STATUS_MESSAGE.get(orderStatus));
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
