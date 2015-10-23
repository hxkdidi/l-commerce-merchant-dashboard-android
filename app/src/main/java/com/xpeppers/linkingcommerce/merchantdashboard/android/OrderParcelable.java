package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Parcel;
import android.os.Parcelable;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Coupon;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;

public class OrderParcelable extends Order implements Parcelable {

    protected OrderParcelable(Parcel in) {
        String[] data = new String[5];
        in.readStringArray(data);
        setStatus(data[0]);
        setTitle(data[1]);
        setBuyerEmail(data[2]);
        setDate(data[3]);
        setCoupon(new Coupon(data[4]));
    }

    public OrderParcelable(Order order){
        this.setStatus(order.getStatus());
        this.setTitle(order.getTitle());
        this.setBuyerEmail(order.getBuyerEmail());
        this.setDate(order.getDate());
        this.setCoupon(order.getCoupon());
    }

    public static final Creator<OrderParcelable> CREATOR = new Creator<OrderParcelable>() {
        @Override
        public OrderParcelable createFromParcel(Parcel in) {
            return new OrderParcelable(in);
        }

        @Override
        public OrderParcelable[] newArray(int size) {
            return new OrderParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        String[] data = new String[5];
        data[0] = getStatus();
        data[1] = getTitle();
        data[2] = getBuyerEmail();
        data[3] = getDate();
        data[4] = getCoupon().getCode();
        out.writeStringArray(data);
    }
}
