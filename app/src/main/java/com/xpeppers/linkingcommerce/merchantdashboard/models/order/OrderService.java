package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface OrderService {

    @GET("/sold_offers")
    void fetchMerchantOrders(Callback<List<Order>> response);

    @PUT("/sold_offers/{id}")
    void changeOrderStatus(@Path("id") int offerId, @Body StatusUpdateBody body, Callback<StatusUpdateResponse> response);
}
