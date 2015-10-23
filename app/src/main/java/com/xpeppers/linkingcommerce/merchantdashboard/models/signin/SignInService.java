package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.AuthToken;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.Credentials;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SignInService {

    @POST("/auth/merchants")
    void signIn(@Body Credentials credentials, Callback<AuthToken> response);
}
