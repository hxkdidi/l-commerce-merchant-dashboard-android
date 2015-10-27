package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.AuthToken;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInPresenter;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInSuccessListener;
import com.xpeppers.linkingcommerce.merchantdashboard.models.signin.SignInView;

import static com.xpeppers.linkingcommerce.merchantdashboard.infrastructure.ServiceFactory.createForSignIn;


public class SignInActivity extends AppCompatActivity implements SignInSuccessListener {

    private SignInPresenter presenter;

    public SignInPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignInView view = new AndroidSignInView(this);

        presenter = new SignInPresenter(view, createForSignIn(), this);
    }

    @Override
    public void signInSuccess(AuthToken authToken) {
        Intent intent = new Intent(this, OrdersListActivity.class);
        intent.putExtra("TOKEN", authToken.getToken());
        startActivity(intent);
    }
}

