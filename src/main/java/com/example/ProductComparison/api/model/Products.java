package com.example.ProductComparison.api.model;
public class Products {
    private String product_one;
    private String product_two;


    private String user_email;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


    public String getProduct_two() {
        return product_two;
    }

    public void setProduct_two(String product_two) {
        this.product_two = product_two;
    }



    public String getProduct_one() {
        return product_one;
    }

    public void setProduct_one(String product_one) {
        this.product_one = product_one;
    }
}