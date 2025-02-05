package com.example.shoppingappliaction;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class DetailsCoffeePojoClass {
    Bitmap mImageView;
    String coffeeName, extraDetails,money;

    public Bitmap getmImageView() {
        return mImageView;
    }

    public void setmImageView(Bitmap mImageView) {
        this.mImageView = mImageView;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public DetailsCoffeePojoClass(Bitmap mImageView, String coffeeName, String extraDetails, String money) {
        this.mImageView = mImageView;
        this.coffeeName = coffeeName;
        this.extraDetails = extraDetails;
        this.money = money;
    }
}
