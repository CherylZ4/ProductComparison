package com.example.ProductComparison.api.model;

import java.util.List;

public class IngredientsResponse {
    public double getPercSil() {
        return percSil;
    }

    public void setPercSil(double percSil) {
        this.percSil = percSil;
    }

    private double percSil;
    private List<String> product_one_ingr;
    private List<String> product_two_ingr;
    private List<String> ingr_common;

    public List<String> getIngr_common() {
        return ingr_common;
    }

    public void setIngr_common(List<String> ingr_common) {
        this.ingr_common = ingr_common;
    }


    public List<String> getProduct_one_ingr() {
        return product_one_ingr;
    }

    public void setProduct_one_ingr(List<String> product_one_ingr) {
        this.product_one_ingr = product_one_ingr;
    }

    public List<String> getProduct_two_ingr() {
        return product_two_ingr;
    }

    public void setProduct_two_ingr(List<String> product_two_ingr) {
        this.product_two_ingr = product_two_ingr;
    }




}
