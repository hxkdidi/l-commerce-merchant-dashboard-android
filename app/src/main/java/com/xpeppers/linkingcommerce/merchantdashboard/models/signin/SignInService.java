package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SignInService {

    @POST("/auth/merchants")
    void signIn(@Body Credentials credentials, Callback<AuthToken> response);
}
