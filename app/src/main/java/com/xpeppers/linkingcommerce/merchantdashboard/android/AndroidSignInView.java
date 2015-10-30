package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.MessageAlert;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInView;

import java.util.Map;

public class AndroidSignInView implements SignInView {

    private Context context;
    private EditText emailField;
    private EditText passwordField;
    private View loginFieldsView;
    private View progressView;
    private MessageAlert alert;

    public AndroidSignInView(final SignInActivity activity) {
        activity.setContentView(R.layout.activity_signin);

        this.emailField = (EditText) activity.findViewById(R.id.email);
        this.passwordField = (EditText) activity.findViewById(R.id.password);
        this.loginFieldsView = activity.findViewById(R.id.login_form);

        this.context = activity;
        this.alert = new AndroidMessageAlert(activity);
        this.progressView = activity.findViewById(R.id.login_progress);

        Button signInButton = (Button) activity.findViewById(R.id.email_sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view, activity);
                activity.getPresenter().signIn();
            }
        });

        this.passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    activity.getPresenter().signIn();
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyboard(View view, SignInActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public String inputPassword() {
        return passwordField.getText().toString();
    }

    @Override
    public String inputEmail() {
        return emailField.getText().toString();
    }

    @Override
    public void loading() {
        progressView.setVisibility(View.VISIBLE);
        loginFieldsView.setVisibility(View.GONE);
    }

    @Override
    public void showCredentialsError() {
        showError(context.getString(R.string.unexisting_merchant), context.getString(R.string.unexisting_merchant_message));
    }

    @Override
    public void showGenericError() {
        showError(context.getString(R.string.generic_error), context.getString(R.string.generic_error_message));
    }

    @Override
    public void resetError() {
        emailField.setError(null);
        passwordField.setError(null);
    }

    private void showError(String title, String message) {
        progressView.setVisibility(View.GONE);
        loginFieldsView.setVisibility(View.VISIBLE);
        alert.show(title, message);
    }

    @Override
    public void showErrors(Map<String, String> validationErrors) {
        if (validationErrors.containsKey("email")) {
            emailField.setError(context.getString(R.string.error_invalid_email));
            emailField.requestFocus();
        } else if (validationErrors.containsKey("password")) {
            passwordField.setError(context.getString(R.string.error_invalid_password));
            passwordField.requestFocus();
        }
    }
}
