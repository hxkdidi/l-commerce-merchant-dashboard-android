package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import java.util.ArrayList;
import java.util.List;

public class OrOrderFilter implements OrderFilter {

    private final List<OrderFilter> orderFilters;

    public OrOrderFilter(){
        this.orderFilters = new ArrayList<OrderFilter>();
    }

    @Override
    public boolean doFilter(Order order) {
        for(OrderFilter filter : orderFilters){
            if(filter.doFilter(order)){
                return true;
            }
        }
        return false;
    }

    public void add(OrderFilter orderFilter) {
        orderFilters.add(orderFilter);
    }
}
