package com.xpeppers.linkingcommerce.merchantdashboard.models.signin;

import com.xpeppers.linkingcommerce.merchantdashboard.models.LoadableView;

import java.util.Map;

public interface SignInView extends LoadableView {

    String inputPassword();

    String inputEmail();

    void showCredentialsError();

    void showGenericError();

    void resetError();

    void showErrors(Map<String, String> validationErrors);
}
