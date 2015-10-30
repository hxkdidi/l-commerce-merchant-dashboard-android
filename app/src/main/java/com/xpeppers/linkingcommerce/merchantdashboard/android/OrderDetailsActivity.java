package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class OrderDetailsActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private OrderDetailsPresenter presenter;

    public Order getOrder() {
        return getIntent().getParcelableExtra("ORDER");
    }

    public void changeStatus(OrderStatus orderStatus) {
        presenter.changeStatus(getOrder().getId(), orderStatus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView emailField = (TextView) findViewById(R.id.buyer_field_text_view);
        TextView titleField = (TextView) findViewById(R.id.order_title_text_view);
        TextView purchaseDateField = (TextView) findViewById(R.id.order_purchase_date_text_view);
        TextView couponCodeField = (TextView) findViewById(R.id.coupon_code_text_view);
        TextView orderStatusField = (TextView) findViewById(R.id.order_state_text_view);
        TextView statusIndicator = (TextView) findViewById(R.id.status_idicator_layout);
        Button changeOrderStatus = (Button) findViewById(R.id.change_order_status_button);

        alertDialog = ChangeOrderStatusDialogFactory.createFor(this);

        changeOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        AndroidOrderDetailsView view = new AndroidOrderDetailsView(this, emailField, titleField, purchaseDateField, couponCodeField, orderStatusField, statusIndicator);

        presenter = new OrderDetailsPresenter(view, ServiceFactory.createForOrders(sessionToken()));
        presenter.show(getOrder());

        enableBackButton();
    }

    private void enableBackButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }

    private String sessionToken() {
        return getIntent().getStringExtra("TOKEN");
    }
}
