package com.pizza4u.models;

public class OrderModel {
    String userEmail;
    String orderId;
    String status;
    Double total;
    String dateTime;
    String longitude;
    String latitude;
    String docId;


    public OrderModel() {
    }

    public OrderModel(String userID,String orderId,String status, Double price, String date, String longitude, String latitude,String docId) {
        this.userEmail =userID;
        this.orderId = orderId;
        this.status=status;
        this.total = price;
        this.dateTime = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.docId=docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
