package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangeOrderStatusDialogFactory {

    static AlertDialog createFor(Activity activity, String currentStatus) {

        int value = getCouponStatusId(activity, currentStatus);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(R.string.choose_status_alert_title)
                .setSingleChoiceItems(R.array.order_statuses, value, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int choice) {
                        Log.d("ALERT", "" + choice);
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    private static int getCouponStatusId(Activity activity, String currentStatus) {
        List<String> orderStatuses = Arrays.asList((activity.getResources().getStringArray(R.array.order_statuses)));
        return orderStatuses.indexOf(currentStatus);
    }
}
