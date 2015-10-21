package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import android.test.AndroidTestCase;

import com.xpeppers.linkingcommerce.merchantdashboard.signin.MessageAlert;

public class AndroidOrdersOrdersViewTest extends AndroidTestCase {

    private SpyMessageAlert alert;

    private AndroidOrdersView view;

    public void setUp() {
        alert = new SpyMessageAlert();

        view = AndroidOrdersView.builder()
                .withContext(getContext())
                .withAlertDialog(alert)
                .build();
    }

    public void xxx() throws Exception {
        assertEquals(true, false);
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
