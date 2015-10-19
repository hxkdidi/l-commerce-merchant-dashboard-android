package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInPresenter implements Callback<AuthToken> {
    private final SignInView signInView;
    private final SignInService signInService;

    public SignInPresenter(SignInView signInView, SignInService signInService) {
        this.signInView = signInView;
        this.signInService = signInService;
    }

    public void signIn() {
        String password = signInView.inputPassword();
        String email = signInView.inputEmail();

        if (!isPasswordValid(password))
            signInView.showPasswordError();
        else if (!isEmailValid(email))
            signInView.showEmailError();
        else {
            Credentials credentials = new Credentials();
            credentials.setEmail(email);
            credentials.setPassword(password);
            signInService.singIn(credentials, this);
            signInView.showSignInProgress();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    @Override
    public void success(AuthToken authToken, Response response) {
        signInView.signInSuccess(authToken);
    }

    @Override
    public void failure(RetrofitError error) {
        if (error.getResponse().getStatus() == 403)
            signInView.showCredentialsError();
        else signInView.showGenericError();
    }
}
