package com.geekspace.a3decommerce.Model;

import java.util.ArrayList;

public class PopularData {
    private ArrayList<Product> popular_products=new ArrayList<>();

    public PopularData(ArrayList<Product> popular_products) {
        this.popular_products = popular_products;
    }

    public ArrayList<Product> getPopular_products() {
        return popular_products;
    }

    public void setPopular_products(ArrayList<Product> popular_products) {
        this.popular_products = popular_products;
    }
}
