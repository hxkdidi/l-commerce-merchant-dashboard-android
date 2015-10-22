package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterTest {

    private static final String INVALID_EMAIL = "devxpeppers.com";
    public static final String INVALID_PASSWORD = "aaa";
    public static final String VALID_EMAIL = "dev@xpeppers.com";
    public static final String VALID_PASSWORD = "1234";

    @Mock private SignInView view;
    @Mock private SignInService service;
    @Mock private SignInSuccessListener successListener;

    private SignInPresenter presenter;

    @Before
    public void setUp() {
        presenter = new SignInPresenter(view, service, successListener);
        when(view.inputPassword()).thenReturn(VALID_PASSWORD);
        when(view.inputEmail()).thenReturn(VALID_EMAIL);
    }

    @Test
    public void
    when_password_length_is_shorter_than_four_chars_then_show_password_error() {
        when(view.inputPassword()).thenReturn(INVALID_PASSWORD);

        presenter.signIn();

        verify(view).showPasswordError();
    }

    @Test
    public void
    when_email_has_invalid_format_then_show_email_error() {
        when(view.inputEmail()).thenReturn(INVALID_EMAIL);

        presenter.signIn();

        verify(view).showEmailError();
    }

    @Test
    public void
    when_email_and_password_are_valid_then_sign_in() {
        presenter.signIn();

        verify(view).loading();
        verify(service).signIn(any(Credentials.class), any(Callback.class));
    }

    @Test
    public void
    when_email_and_password_are_not_valid_then_do_not_sign_in() {
        when(view.inputPassword()).thenReturn(INVALID_PASSWORD);
        when(view.inputEmail()).thenReturn(INVALID_EMAIL);

        presenter.signIn();

        verify(view, never()).loading();
        verify(service, never()).signIn(any(Credentials.class), any(Callback.class));
    }

    @Test
    public void
    when_sign_in_is_called_then_resetError(){
        presenter.signIn();

        verify(view).resetError();
    }

    @Test
    public void
    when_credentials_are_not_correct_then_showCredentialsError(){
        Response response = new Response("", 401, "", new ArrayList(), null);
        RetrofitError retrofitError = RetrofitError.httpError("", response, null, null);

        presenter.failure(retrofitError);

        verify(view).showCredentialsError();
    }

    @Test
    public void
    when_generic_error_occurs_then_showGenericError() {
        Response response = new Response("", 500, "", new ArrayList(), null);
        RetrofitError retrofitError = RetrofitError.httpError("", response, null, null);

        presenter.failure(retrofitError);

        verify(view).showGenericError();
    }

    @Test
    public void
    when_network_error_then_showGenericError(){
        RetrofitError retrofitError = RetrofitError.networkError("", new IOException());

        presenter.failure(retrofitError);

        verify(view).showGenericError();
    }

    @Test
    public void
    when_signIn_is_successful_then_signInSuccess() {
        AuthToken authToken = new AuthToken();
        authToken.setToken("88YY99ZZ");
        Response response = new Response("", 201, "", new ArrayList(), null);

        presenter.success(authToken, response);

        verify(successListener).signInSuccess(eq(authToken));
    }
}
