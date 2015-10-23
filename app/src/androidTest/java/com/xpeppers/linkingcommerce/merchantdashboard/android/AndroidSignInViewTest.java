package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.EditText;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.MessageAlert;

public class AndroidSignInViewTest extends AndroidTestCase {

    private EditText inputEmailField;
    private EditText inputPasswordField;
    private AndroidSignInView signInView;
    private View progressView;
    private View loginFieldsView;
    private SpyMessageAlert alertDialog;

    public void setUp() {
        alertDialog = new SpyMessageAlert();
        inputEmailField = new EditText(getContext());
        inputPasswordField = new EditText(getContext());
        progressView = new View(getContext());
        loginFieldsView = new View(getContext());
        signInView = AndroidSignInView.builder()
                .withContext(getContext())
                .withEmailField(inputEmailField)
                .withPasswordField(inputPasswordField)
                .withProgressView(progressView)
                .withLoginFieldsView(loginFieldsView)
                .withAlertDialog(alertDialog)
                .build();
    }

    public void testReturnEmailValue() throws Exception {
        inputEmailField.setText("dev@xpeppers.com");
        assertEquals("dev@xpeppers.com", signInView.inputEmail());
    }

    public void testReturnPasswordValue() throws Exception {
        inputPasswordField.setText("1234");
        assertEquals("1234", signInView.inputPassword());
    }

    public void testShowEmailError() {
        signInView.showEmailError();
        assertEquals(getContext().getString(R.string.error_invalid_email), inputEmailField.getError());
        assertTrue(inputEmailField.hasFocus());
    }

    public void testShowPasswordError() {
        signInView.showPasswordError();
        assertEquals(getContext().getString(R.string.error_invalid_password), inputPasswordField.getError());
        assertTrue(inputPasswordField.hasFocus());
    }

    public void testShowProgressView() {
        signInView.loading();
        assertEquals(View.VISIBLE, progressView.getVisibility());
        assertEquals(View.GONE, loginFieldsView.getVisibility());
    }

    public void testShowCredentialsError() {
        signInView.showCredentialsError();
        assertEquals(View.GONE, progressView.getVisibility());
        assertEquals(View.VISIBLE, loginFieldsView.getVisibility());
        assertEquals(getContext().getString(R.string.unexisting_merchant), alertDialog.getTitle());
        assertEquals(getContext().getString(R.string.unexisting_merchant_message), alertDialog.getMessage());
    }

    public void testShowGenericError() {
        signInView.showGenericError();
        assertEquals(View.GONE, progressView.getVisibility());
        assertEquals(View.VISIBLE, loginFieldsView.getVisibility());
        assertEquals(getContext().getString(R.string.generic_error), alertDialog.getTitle());
        assertEquals(getContext().getString(R.string.generic_error_message), alertDialog.getMessage());
    }

    public void testResetError(){
        signInView.showEmailError();
        signInView.showPasswordError();
        signInView.resetError();
        assertNull(inputPasswordField.getError());
        assertNull(inputEmailField.getError());
    }


    public class SpyMessageAlert implements MessageAlert {

        private String title;
        private String message;

        @Override
        public void showMessage(String title, String message) {
            this.title = title;
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }
    }
}
