package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.Activity;
import android.widget.Toast;

import com.xpeppers.linkingcommerce.merchantdashboard.models.MessageAlert;

public class AndroidMessageAlert implements MessageAlert {

    private Activity activity;

    public AndroidMessageAlert(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void show(String title, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
