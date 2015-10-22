package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface OrderService {

    @GET("/sold_offers")
    void fetchMerchantOrders(Callback<List<Order>> response);
}
