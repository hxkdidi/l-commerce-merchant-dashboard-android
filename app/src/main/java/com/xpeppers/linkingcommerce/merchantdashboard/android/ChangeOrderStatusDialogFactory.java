package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class ChangeOrderStatusDialogFactory {

    static AlertDialog createFor(final OrderDetailsActivity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(R.string.choose_status_alert_title)
                .setSingleChoiceItems(R.array.order_statuses, 0, null)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        int selected = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        activity.changeStatus(OrderStatus.values()[selected]);
                    }
                });

        return builder.create();
    }

}
