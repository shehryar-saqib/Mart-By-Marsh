package com.example.martbymarsh;

public class cartModel {
    String image3,item3;
    int price3;//,quantity;//,count;

    public  cartModel(){}

    public cartModel(String image3, String item3, int price3){//, int quantity) {
        this.image3 = image3;
        this.item3 = item3;
        this.price3 = price3;
      //  this.quantity = quantity;
    }



    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public int getPrice3() {
        return price3;
    }

    public void setPrice3(int price3) {
        this.price3 = price3;
    }

    //public int getQuantity() {
      //  return quantity;
    //}

    //public void setQuantity(int quantity) {
      //  this.quantity = quantity;
    //}
}
