package com.yubin.apsfc.dean;

public class Menus {
    private int menuid;
    private String menuName;
    private String typename;
    private String burden;
    private String brief;
    private int price;
    private int pricel;

    public Menus(int menuid, String menuName, String typename, String burden, String brief, int price, int pricel) {
        this.menuid = menuid;
        this.menuName = menuName;
        this.typename = typename;
        this.burden = burden;
        this.brief = brief;
        this.price = price;
        this.pricel = pricel;
    }

    public Menus() {
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPricel() {
        return pricel;
    }

    public void setPricel(int pricel) {
        this.pricel = pricel;
    }
}
