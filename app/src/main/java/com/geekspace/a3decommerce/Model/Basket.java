package com.geekspace.a3decommerce.Model;

public class Basket {
    private int id;
    private String name;
    private String image;
    private String categoryName;
    private Double price;
    private int count;

    public Basket(int id, String name, String image, String categoryName, Double price, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.categoryName = categoryName;
        this.price = price;
        this.count = count;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
