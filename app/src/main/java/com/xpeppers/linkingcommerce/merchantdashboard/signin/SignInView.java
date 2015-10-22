package com.xpeppers.linkingcommerce.merchantdashboard.signin;

public interface SignInView {
    String inputPassword();

    String inputEmail();

    void showPasswordError();

    void showEmailError();

    void showSignInProgress();

    void showCredentialsError();

    void showGenericError();

    void resetError();
}
