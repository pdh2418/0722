package com.yubin.apsfc.dean;

public class Order {
    private String username;
    private String fooName;
    private int foodNum;
    private String foodTime;

    public Order(String username, String fooName, int foodNum, String foodTime) {
        this.username = username;
        this.fooName = fooName;
        this.foodNum = foodNum;
        this.foodTime = foodTime;
    }

    public Order() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFooName() {
        return fooName;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }
}
