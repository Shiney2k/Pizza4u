package com.pizza4u;

public class OrderItemModel {
    String pizzaName;
    int count;
    Float price;

    public OrderItemModel() {
    }

    public OrderItemModel(String pizzaName, int count, Float price) {
        this.pizzaName = pizzaName;
        this.count = count;
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
