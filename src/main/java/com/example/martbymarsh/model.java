package com.example.martbymarsh;

public class model {
    String item,img;
    int price;
    public model(){}

    public model(String item, int price, String img) {
        this.item = item;
        this.price = price;
        this.img = img;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
