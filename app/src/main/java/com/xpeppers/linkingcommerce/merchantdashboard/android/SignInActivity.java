package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.AuthToken;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInService;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInSuccessListener;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInView;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForSignIn;


public class SignInActivity extends AppCompatActivity {

    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignInView view = new AndroidSignInView(this);

        SignInService service = createForSignIn();
        SignInSuccessListener successListener = createSuccessListener();

        presenter = new SignInPresenter(view, service, successListener);
    }

    public SignInPresenter getPresenter() {
        return presenter;
    }

    @NonNull
    private SignInSuccessListener createSuccessListener() {
        return new SignInSuccessListener() {
                @Override
                public void signInSuccess(AuthToken authToken) {
                    Intent intent = new Intent(SignInActivity.this, OrdersListActivity.class);
                    intent.putExtra("TOKEN", authToken.getToken());
                    startActivity(intent);
                }
            };
    }
}

