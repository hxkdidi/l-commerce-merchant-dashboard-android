package com.xpeppers.linkingcommerce.merchantdashboard.models;

import java.util.Map;

public interface Validator {

    Map<String, String> validate(String email, String password);

}
