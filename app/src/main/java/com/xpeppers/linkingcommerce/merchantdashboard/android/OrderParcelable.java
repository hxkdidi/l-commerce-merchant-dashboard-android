package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.os.Parcel;
import android.os.Parcelable;

import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Coupon;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.Order;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class OrderParcelable extends Order implements Parcelable {

    protected OrderParcelable(Parcel in) {
        String[] data = new String[6];
        in.readStringArray(data);
        setId(parseInt(data[0]));
        setStatus(data[1]);
        setTitle(data[2]);
        setBuyerEmail(data[3]);
        setDate(data[4]);
        setCoupon(new Coupon(data[5]));
    }

    public OrderParcelable(Order order){
        this.setId(order.getId());
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
        String[] data = new String[6];
        data[0] = valueOf(getId());
        data[1] = getStatus();
        data[2] = getTitle();
        data[3] = getBuyerEmail();
        data[4] = getDate();
        data[5] = getCoupon().getCode();
        out.writeStringArray(data);
    }
}
