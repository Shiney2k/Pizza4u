package com.pizza4u.models;

public class PizzaTypeModel {
    String typeID,typeName,photo_url;

    public PizzaTypeModel() {
    }

    public PizzaTypeModel(String typeID,String typeName, String photo_url) {
        this.typeID=typeID;
        this.typeName = typeName;
        this.photo_url = photo_url;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
