package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInPresenter implements Callback<AuthToken> {
    private final SignInView view;
    private final SignInService service;
    private SignInSuccessListener successListener;

    public SignInPresenter(SignInView view, SignInService service, SignInSuccessListener successListener) {
        this.view = view;
        this.service = service;
        this.successListener = successListener;
    }

    public void signIn() {
        view.resetError();

        String password = view.inputPassword();
        String email = view.inputEmail();

        if (!isEmailValid(email))
            view.showEmailError();
        else if (!isPasswordValid(password))
            view.showPasswordError();
        else {
            Credentials credentials = new Credentials();
            credentials.setEmail(email);
            credentials.setPassword(password);
            service.signIn(credentials, this);
            view.showSignInProgress();
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
        successListener.signInSuccess(authToken);
    }

    @Override
    public void failure(RetrofitError error) {
        if ((error.getResponse() == null) || error.getResponse().getStatus() != 401) {
            view.showGenericError();
        } else {
            view.showCredentialsError();
        }
    }
}
