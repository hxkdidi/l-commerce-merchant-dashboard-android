package com.xpeppers.linkingcommerce.merchantdashboard.orders;

import com.google.gson.annotations.SerializedName;

public class Order {

    private String title;
    @SerializedName(value="buyer_email")
    private String buyerEmail;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyer_email) {
        this.buyerEmail = buyer_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
