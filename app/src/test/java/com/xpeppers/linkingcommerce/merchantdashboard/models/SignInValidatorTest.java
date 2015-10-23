package com.xpeppers.linkingcommerce.merchantdashboard.models;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertTrue;

public class SignInValidatorTest {

    private static final String VALID_EMAIL = "info@merchant.com";
    private static final String INVALID_EMAIL = "@merchant.com";

    public static final String VALID_PASSWORD = "abcd";
    private static final String INVALID_PASSWORD = "abc";

    @Test
    public void validates_password_length() {
        Validator validator = new SignInValidator();

        Map<String, String> validationErrors = validator.validate(VALID_EMAIL, INVALID_PASSWORD);

        assertTrue(validationErrors.containsKey("password"));
    }

    @Test
    public void validates_email_format() {
        Validator validator = new SignInValidator();

        Map<String, String> validationErrors = validator.validate(INVALID_EMAIL, VALID_PASSWORD);

        assertTrue(validationErrors.containsKey("email"));
    }
}
