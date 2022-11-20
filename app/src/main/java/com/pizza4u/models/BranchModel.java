package com.pizza4u.models;

public class BranchModel {
    String branchname;
    String branchid;
    String locationname;
    String longitude;
    String latitude;

    public BranchModel() {
    }

    String docid;

    public BranchModel(String branchname, String branchid, String locationname, String longitude, String latitude, String docid) {
        this.branchname = branchname;
        this.branchid = branchid;
        this.locationname = locationname;
        this.longitude = longitude;
        this.latitude = latitude;
        this.docid = docid;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
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

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }
}
