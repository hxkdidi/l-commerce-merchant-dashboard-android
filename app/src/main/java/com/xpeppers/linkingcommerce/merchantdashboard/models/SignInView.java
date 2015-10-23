package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.models.LoadableView;

public interface SignInView extends LoadableView {

    String inputPassword();

    String inputEmail();

    void showPasswordError();

    void showEmailError();

    void showCredentialsError();

    void showGenericError();

    void resetError();
}
