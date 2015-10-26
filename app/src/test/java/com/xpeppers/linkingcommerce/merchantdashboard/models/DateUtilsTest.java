package com.xpeppers.linkingcommerce.merchantdashboard.models;

import com.xpeppers.linkingcommerce.merchantdashboard.utils.DateUtils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void it_converts_date_to_from_iso8601() {
        String convertedDate = DateUtils.convertFromISO8601("2015-09-28T13:55:57Z");

        assertEquals("28/09/2015 13:55", convertedDate);
    }

}
