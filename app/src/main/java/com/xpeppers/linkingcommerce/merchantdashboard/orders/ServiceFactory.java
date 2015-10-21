package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.support.annotation.NonNull;

import com.xpeppers.linkingcommerce.merchantdashboard.Constants;
import com.xpeppers.linkingcommerce.merchantdashboard.signin.SignInService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ServiceFactory {

    static OrderService createForOrders(final String sessionToken) {
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
                    request.addHeader("Token", "token=" + sessionToken);
                }
            };
    }

}
