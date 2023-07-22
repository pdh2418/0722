package com.yubin.apsfc.dean;

public class User {
   private   int  id;
    private   String username;
    private  String userpassword;
    private  String  sex;
    private  String   address;
    private  String  phone;
    private   String email;
    private String grade;

    public User(int id, String username, String userpassword, String sex, String address, String phone, String email, String grade) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
    }

    public User() {
    }

    public User(String username, String pwd, String sex, String address, String phones, String email, String 用户) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
