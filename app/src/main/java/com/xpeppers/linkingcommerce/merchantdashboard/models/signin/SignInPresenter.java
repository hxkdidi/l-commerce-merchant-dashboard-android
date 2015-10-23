package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInPresenter implements Callback<AuthToken> {
    public static final int MINIMUM_PASSWORD_LENGTH = 4;
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
            view.loading();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= MINIMUM_PASSWORD_LENGTH;
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
