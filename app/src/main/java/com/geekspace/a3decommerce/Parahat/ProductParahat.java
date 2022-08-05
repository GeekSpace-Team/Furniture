package com.geekspace.a3decommerce.Parahat;

import com.google.gson.annotations.SerializedName;

public class ProductParahat {
    int id;
    String product_id;
    int categoryId;
    @SerializedName("product_images")
    int image;
    @SerializedName("product_name_tm")
    String name;
    String product_name_ru;
    @SerializedName("categoryName")
    String category;
    @SerializedName("product_price")
    double cost;
    double product_discount;
    boolean isFav;
    boolean is_popular;
    String png;

    public ProductParahat(int id, String product_id, int categoryId, int image, String name, String product_name_ru, String category, double cost, double product_discount, boolean isFav, boolean is_popular, String png) {
        this.id = id;
        this.product_id = product_id;
        this.categoryId = categoryId;
        this.image = image;
        this.name = name;
        this.product_name_ru = product_name_ru;
        this.category = category;
        this.cost = cost;
        this.product_discount = product_discount;
        this.isFav = isFav;
        this.is_popular = is_popular;
        this.png = png;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_name_ru() {
        return product_name_ru;
    }

    public void setProduct_name_ru(String product_name_ru) {
        this.product_name_ru = product_name_ru;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProduct_discount() {
        return product_discount;
    }

    public void setProduct_discount(double product_discount) {
        this.product_discount = product_discount;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isIs_popular() {
        return is_popular;
    }

    public void setIs_popular(boolean is_popular) {
        this.is_popular = is_popular;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
