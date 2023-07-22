package com.yubin.dvd.bean;

public class Dvd {
    private int id;
    private String name;
    private String state;
    private String date;


    public Dvd(int id, String name, String state, String date) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.date = date;
    }

    public Dvd() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
