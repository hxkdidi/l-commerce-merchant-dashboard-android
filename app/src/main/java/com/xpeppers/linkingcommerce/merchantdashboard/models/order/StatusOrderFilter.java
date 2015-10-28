package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

public class StatusOrderFilter implements OrderFilter {

    private String statusFilter;

    public StatusOrderFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    @Override
    public boolean doFilter(Order order) {
        return order.getStatus().equals(statusFilter);
    }
}
