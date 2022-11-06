package com.pizza4u.models;

public class CartItemModel {
    String pizzaName;
    Float price;
    int count;

    public CartItemModel() {
    }

    public CartItemModel(String pizzaName, Float price, int count) {
        this.pizzaName = pizzaName;
        this.price = price;
        this.count = count;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
