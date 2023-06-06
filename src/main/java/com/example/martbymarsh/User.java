package com.example.martbymarsh;

public class User {
    String name,email,phone_no,address,password;
    int count,total;


    public User(){}
    public User(String name, String email,String phone_no,String address,String password,int count,int total){
        this.name=name;
        this.email=email;
        this.phone_no=phone_no;
        this.address=address;
        this.password=password;
        this.count=count;
        this.total=total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
