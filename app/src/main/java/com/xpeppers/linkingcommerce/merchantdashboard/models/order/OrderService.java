package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface OrderService {

    @GET("/sold_offers")
    void fetchMerchantOrders(Callback<List<Order>> response);
}
