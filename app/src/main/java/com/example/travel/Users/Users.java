package com.example.travel.Users;

public class Users {
    int id;
    String name,b_date,phone,email,password;

    public Users(String name, String b_date, String phone, String email, String password) {
        this.name = name;
        this.b_date = b_date;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Users(int id, String name, String b_date, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.b_date = b_date;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getB_date() {
        return b_date;
    }

    public void setB_date(String b_date) {
        this.b_date = b_date;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
