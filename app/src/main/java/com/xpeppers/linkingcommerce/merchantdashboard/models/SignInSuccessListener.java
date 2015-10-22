package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.AuthToken;

public interface SignInSuccessListener {

    void signInSuccess(AuthToken authToken);

}
