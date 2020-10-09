package com.techelevator.Machine;

import java.math.BigDecimal;

public class Item {
    //constant used in constructor
    private static final int DEFAULT_QUANTITY = 5;

    private String name;
    private BigDecimal price;
    private String itemCode;
    private String foodType;
    private int quantity;
    private String sound;

    //constructor
    public Item(String itemCode, String name, BigDecimal price, String foodType) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.quantity = DEFAULT_QUANTITY;
        if (foodType.equals("Chips")) {
            sound = "Crunch Crunch, Yum!";
        } else if (foodType.equals("Candy")) {
            sound = "Munch Munch, Yum!";
        } else if (foodType.equals("Drink")) {
            sound = "Glug Glug, Yum!";
        } else {
            sound = "Chew Chew, Yum!";
        }
    }

    //getters, setters
    public String getItemCode() {
        return itemCode;
    }

    public String getPrice() {
        return price.toString();
    }

    public BigDecimal getPriceAsDecimal() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return sound;
    }
}
