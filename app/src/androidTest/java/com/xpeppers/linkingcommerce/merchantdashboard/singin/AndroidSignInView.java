package com.xpeppers.linkingcommerce.merchantdashboard.singin;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.signin.SignInView;

public class AndroidSignInView implements SignInView {
    private Context context;
    private EditText emailField;
    private EditText passwordField;
    private View loginFieldsView;
    private View progressView;
    private MessageAlert messageAlert;

    private AndroidSignInView() {

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
    public void showEmailError() {
        emailField.setError(context.getString(R.string.error_invalid_email));
        emailField.requestFocus();
    }

    @Override
    public void showPasswordError() {
        passwordField.setError(context.getString(R.string.error_invalid_password));
        passwordField.requestFocus();
    }

    @Override
    public void showSignInProgress() {
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
        messageAlert.showMessage(title, message);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final AndroidSignInView androidSignInView;

        private Builder() {
            androidSignInView = new AndroidSignInView();
        }

        public Builder withContext(Context context) {
            androidSignInView.context = context;
            return this;
        }

        public Builder withEmailField(EditText emailField) {
            androidSignInView.emailField = emailField;
            return this;
        }

        public Builder withPasswordField(EditText passwordField) {
            androidSignInView.passwordField = passwordField;
            return this;
        }

        public Builder withProgressView(View progressView) {
            androidSignInView.progressView = progressView;
            return this;
        }

        public Builder withLoginFieldsView(View loginFieldsView) {
            androidSignInView.loginFieldsView = loginFieldsView;
            return this;
        }

        public Builder withAlertDialog(MessageAlert messageAlert) {
            androidSignInView.messageAlert = messageAlert;
            return this;
        }

        public AndroidSignInView build() {
            return androidSignInView;
        }
    }

}
