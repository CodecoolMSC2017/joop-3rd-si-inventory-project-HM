package com.codecool;

public abstract class Product {

    String name;
    int price;

    public Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){

        return name;
    }

    public int getPrice(){

        return price;
    }
}
