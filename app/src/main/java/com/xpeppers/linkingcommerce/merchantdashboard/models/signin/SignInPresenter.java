package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import com.xpeppers.linkingcommerce.merchantdashboard.models.SignInValidator;
import com.xpeppers.linkingcommerce.merchantdashboard.models.Validator;

import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInPresenter implements Callback<AuthToken> {

    private final SignInView view;
    private final SignInService service;
    private final Validator validator;
    private SignInSuccessListener successListener;

    public SignInPresenter(SignInView view, SignInService service, SignInSuccessListener successListener) {
        this(view, service, new SignInValidator(), successListener);
    }

    public SignInPresenter(SignInView view, SignInService service, Validator validator, SignInSuccessListener successListener) {
        this.view = view;
        this.service = service;
        this.validator = validator;
        this.successListener = successListener;
    }

    public void signIn() {
        view.resetError();

        String email = view.inputEmail();
        String password = view.inputPassword();

        Map<String, String> validationErrors = validator.validate(email, password);

        if (validationErrors.containsKey("email")) {
            view.showEmailError();
        } else if (validationErrors.containsKey("password")) {
            view.showPasswordError();
        } else {
            Credentials credentials = new Credentials();
            credentials.setEmail(email);
            credentials.setPassword(password);
            service.signIn(credentials, this);
            view.loading();
        }
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
