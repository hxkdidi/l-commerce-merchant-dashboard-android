package com.xpeppers.linkingcommerce.merchantdashboard.infrastructure;

import android.support.annotation.NonNull;

import com.xpeppers.linkingcommerce.merchantdashboard.Constants;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ServiceFactory {

    public static OrderService createForOrders(final String sessionToken) {
        RequestInterceptor authenticatedRequest = authenticatedRequest(sessionToken);

        RestAdapter adapter = adapterBuilder()
                .setRequestInterceptor(authenticatedRequest)
                .build();

        return adapter.create(OrderService.class);
    }

    public static SignInService createForSignIn() {
        RestAdapter adapter = adapterBuilder().build();

        return adapter.create(SignInService.class);
    }

    private static RestAdapter.Builder adapterBuilder() {
        return new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(Constants.BACKOFFICE_URL);
    }

    @NonNull
    private static RequestInterceptor authenticatedRequest(final String sessionToken) {
        return new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", "Token token=" + sessionToken);
                }
            };
    }

}
