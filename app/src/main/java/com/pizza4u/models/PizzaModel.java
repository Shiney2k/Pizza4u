package com.pizza4u.models;

public class PizzaModel {
    String name,description,photo_url,pizza_type;
    Float price;

    public PizzaModel() {
    }

    public PizzaModel(String name,String description,Float price,String photo_url,String pizza_type) {
        this.name=name;
        this.description = description;
        this.price=price;
        this.photo_url=photo_url;
        this.pizza_type=pizza_type;
    }

    public String getName() {
        return name;
    }

    public String getPizza_type() {
        return pizza_type;
    }

    public void setPizza_type(String pizza_type) {
        this.pizza_type = pizza_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
