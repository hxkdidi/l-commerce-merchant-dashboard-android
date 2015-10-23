package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.AuthToken;

public interface SignInSuccessListener {

    void signInSuccess(AuthToken authToken);

}
