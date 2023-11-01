package com.heroku.java.model;

public class Car {

    String color = "";
    String brand = "";
    int hestekrafter = 0;

    public Car(String color, String brand, int hestekrafter) {
        this.color = color;
        this.brand = brand;
        this.hestekrafter = hestekrafter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getHestekrafter() {
        return hestekrafter;
    }

    public void setHestekrafter(int hestekrafter) {
        this.hestekrafter = hestekrafter;
    }
}
