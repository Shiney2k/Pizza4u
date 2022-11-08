package com.pizza4u.models;

import com.google.type.DateTime;

public class OrderModel {
    String orderId;
    Float price;
    DateTime date;
    Double longitude;
    Double latitude;


    public OrderModel() {
    }

    public OrderModel(String orderId, Float price, DateTime date, Double longitude, Double latitude) {
        this.orderId = orderId;
        this.price = price;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
