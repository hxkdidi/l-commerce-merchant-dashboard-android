package com.xpeppers.linkingcommerce.merchantdashboard.signin;

public interface SignInView {
    public String inputPassword();

    public String inputEmail();

    public void showPasswordError();

    public void showEmailError();

    public void showSignInProgress();

    public void showCredentialsError();

    public void showGenericError();

    public void signInSuccess(AuthToken authToken);
}
