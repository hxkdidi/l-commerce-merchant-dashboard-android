package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInPresenter implements Callback<AuthToken> {
    private final SignInView signInView;
    private final SignInService signInService;
    private SignInSuccessListener signInSuccessListener;

    public SignInPresenter(SignInView signInView, SignInService signInService, SignInSuccessListener signInSuccessListener) {
        this.signInView = signInView;
        this.signInService = signInService;
        this.signInSuccessListener = signInSuccessListener;
    }

    public void signIn() {
        signInView.resetError();

        String password = signInView.inputPassword();
        String email = signInView.inputEmail();

        if (!isEmailValid(email))
            signInView.showEmailError();
        else if (!isPasswordValid(password))
            signInView.showPasswordError();
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
        signInSuccessListener.signInSuccess(authToken);
    }

    @Override
    public void failure(RetrofitError error) {
        if ((error.getResponse() == null) || error.getResponse().getStatus() != 401) {
            signInView.showGenericError();
        } else {
            signInView.showCredentialsError();
        }
    }
}
