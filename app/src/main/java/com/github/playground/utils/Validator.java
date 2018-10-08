package com.github.playground.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validator {
    public static boolean isValid(String s) {
        return !TextUtils.isEmpty(s);
    }

    public static boolean isAlphaNumeric(String s) {
        boolean isValid = true;
        if (!s.matches(".*[0-9].*")) {
            isValid = false;
        } else if (!s.matches(".*[A-Z].*")) {
            if (!s.matches(".*[a-z].*")) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static boolean isValidEmailId(String emailId) {
        return isValid(emailId) && Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
    }

}
