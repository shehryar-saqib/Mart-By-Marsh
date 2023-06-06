package com.example.martbymarsh;

public class CategoryModel {
    String image,product;

    public CategoryModel(){}

    public CategoryModel(String image, String product) {
        this.image = image;
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
