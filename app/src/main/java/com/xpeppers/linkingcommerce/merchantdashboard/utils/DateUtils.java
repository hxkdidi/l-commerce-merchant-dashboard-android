package com.xpeppers.linkingcommerce.merchantdashboard.utils;

public class DateUtils {

    public static String convertFromISO8601(final String dateString) {
        try {
            String[] splitISO8601String = dateString.split("T");

            String[] splitDate = splitISO8601String[0].split("-");
            String convertedDate = splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];

            String[] splitTime = splitISO8601String[1].split(":");
            String convertTime = splitTime[0] + ":" + splitTime[1];

            return convertedDate + " " + convertTime;
        } catch (Exception ex) {
            return "";
        }
    }

}
