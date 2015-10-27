package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.xpeppers.linkingcommerce.merchantdashboard.R;

public class ChangeOrderStatusDialogFactory {

    static AlertDialog createFor(final OrderDetailsActivity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(R.string.choose_status_alert_title)
                .setSingleChoiceItems(R.array.order_statuses, 0, null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        int selected = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        String newStatus = "used";
                        Log.d("position", "" + selected);
                        activity.getPresenter().changeStatusTo(1, newStatus);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

}
