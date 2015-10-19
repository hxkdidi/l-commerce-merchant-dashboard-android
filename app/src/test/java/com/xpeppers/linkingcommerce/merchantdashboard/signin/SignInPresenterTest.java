package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignInPresenterTest {

    private SignInView signInView;
    private SignInPresenter signInPresenter;
    private SignInService signInService;
    private SignInSuccessListener signInSuccessListener;

    @Before
    public void setUp() {
        signInView = mock(SignInView.class);
        signInService = mock(SignInService.class);
        signInSuccessListener = mock(SignInSuccessListener.class);
        signInPresenter = new SignInPresenter(signInView, signInService, signInSuccessListener);
    }

    @Test
    public void
    test_when_password_lenght_is_less_than_four_chars_then_show_password_error() {
        when(signInView.inputPassword()).thenReturn("12");
        when(signInView.inputEmail()).thenReturn("dev@xpeppers.com");
        signInPresenter.signIn();
        verify(signInView).showPasswordError();
    }

    @Test
    public void
    test_when_email_has_not_at_then_show_email_error() {
        when(signInView.inputPassword()).thenReturn("1234");
        when(signInView.inputEmail()).thenReturn("devxpeppers.com");
        signInPresenter.signIn();
        verify(signInView).showEmailError();
    }

    @Test
    public void
    test_when_email_and_password_are_valid_then_showSignInProgress() {
        when(signInView.inputPassword()).thenReturn("1234");
        when(signInView.inputEmail()).thenReturn("dev@xpeppers.com");
        signInPresenter.signIn();
        verify(signInView).showSignInProgress();
    }

    @Test
    public void
    test_when_email_and_password_are_valid_then_signIn() {
        when(signInView.inputPassword()).thenReturn("1234");
        when(signInView.inputEmail()).thenReturn("dev@xpeppers.com");
        signInPresenter.signIn();
        verify(signInService).singIn(any(Credentials.class), any(Callback.class));
    }

    @Test
    public void
    test_when_email_and_password_are_not_valid_then_not_showSignInProgress() {
        when(signInView.inputPassword()).thenReturn("12");
        when(signInView.inputEmail()).thenReturn("devxpeppers.com");
        signInPresenter.signIn();
        verify(signInView, never()).showSignInProgress();
        verify(signInService, never()).singIn(any(Credentials.class), any(Callback.class));
    }

    @Test
    public void
    test_when_sign_is_called_then_resetError(){
        when(signInView.inputPassword()).thenReturn("1234");
        when(signInView.inputEmail()).thenReturn("dev@xpeppers.com");
        signInPresenter.signIn();
        verify(signInView).resetError();
    }

    @Test
    public void
    test_when_credentials_are_not_correct_then_showCredentialsError(){
        Response response = new Response("", 403, "", new ArrayList(), null);
        RetrofitError retrofitError = RetrofitError.httpError("", response, null, null);
        signInPresenter.failure(retrofitError);
        verify(signInView).showCredentialsError();
    }

    @Test
    public void
    test_when_generic_error_occurs_then_showGenericError() {
        Response response = new Response("", 500, "", new ArrayList(), null);
        RetrofitError retrofitError = RetrofitError.httpError("", response, null, null);
        signInPresenter.failure(retrofitError);
        verify(signInView).showGenericError();
    }

    @Test
    public void
    test_when_signIn_is_successful_then_signInSuccess() {
        AuthToken authToken = new AuthToken();
        authToken.setToken("88YY99ZZ");
        Response response = new Response("", 201, "", new ArrayList(), null);
        signInPresenter.success(authToken, response);
        verify(signInSuccessListener).signInSuccess(eq(authToken));
    }
}
