package com.geekspace.a3decommerce.Model;

import java.util.ArrayList;

public class HomeData {
    private ArrayList<Banner> banners=new ArrayList<>();
    private ArrayList<Category> categories=new ArrayList<>();
    private ArrayList<Product> products=new ArrayList<>();

    public HomeData(ArrayList<Banner> banners, ArrayList<Category> categories, ArrayList<Product> products) {
        this.banners = banners;
        this.categories = categories;
        this.products = products;
    }

    public ArrayList<Banner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<Banner> banners) {
        this.banners = banners;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
