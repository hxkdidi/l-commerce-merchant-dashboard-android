package com.xpeppers.linkingcommerce.merchantdashboard;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SignInService {

    @POST("/auth/merchants")
    void singIn(@Body Credentials credentials, Callback<AuthToken> response);
}
