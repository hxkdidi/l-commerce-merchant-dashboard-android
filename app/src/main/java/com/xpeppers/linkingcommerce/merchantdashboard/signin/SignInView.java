package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import com.xpeppers.linkingcommerce.merchantdashboard.orders.LoadableView;

public interface SignInView extends LoadableView {

    String inputPassword();

    String inputEmail();

    void showPasswordError();

    void showEmailError();

    void showCredentialsError();

    void showGenericError();

    void resetError();
}
