package com.geekspace.a3decommerce.Model;

public class SingleProduct {
    private int id;
    private String product_id;
    private int categoryId;
    private String categoryName;
    private String product_name_tm;
    private String product_name_ru;
    private String product_description_tm;
    private String product_description_ru;
    private double product_price;
    private double product_discount;
    private boolean is_popular;
    private String product_color_types;
    private String product_images;
    private String product_rotating_image;
    private String createdAt;
    private String updatedAt;

    public SingleProduct(int id, String product_id, int categoryId, String categoryName, String product_name_tm, String product_name_ru, String product_description_tm, String product_description_ru, double product_price, double product_discount, boolean is_popular, String product_color_types, String product_images, String product_rotating_image, String createdAt, String updatedAt) {
        this.id = id;
        this.product_id = product_id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.product_name_tm = product_name_tm;
        this.product_name_ru = product_name_ru;
        this.product_description_tm = product_description_tm;
        this.product_description_ru = product_description_ru;
        this.product_price = product_price;
        this.product_discount = product_discount;
        this.is_popular = is_popular;
        this.product_color_types = product_color_types;
        this.product_images = product_images;
        this.product_rotating_image = product_rotating_image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProduct_name_tm() {
        return product_name_tm;
    }

    public void setProduct_name_tm(String product_name_tm) {
        this.product_name_tm = product_name_tm;
    }

    public String getProduct_name_ru() {
        return product_name_ru;
    }

    public void setProduct_name_ru(String product_name_ru) {
        this.product_name_ru = product_name_ru;
    }

    public String getProduct_description_tm() {
        return product_description_tm;
    }

    public void setProduct_description_tm(String product_description_tm) {
        this.product_description_tm = product_description_tm;
    }

    public String getProduct_description_ru() {
        return product_description_ru;
    }

    public void setProduct_description_ru(String product_description_ru) {
        this.product_description_ru = product_description_ru;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public double getProduct_discount() {
        return product_discount;
    }

    public void setProduct_discount(double product_discount) {
        this.product_discount = product_discount;
    }

    public boolean isIs_popular() {
        return is_popular;
    }

    public void setIs_popular(boolean is_popular) {
        this.is_popular = is_popular;
    }

    public String getProduct_color_types() {
        return product_color_types;
    }

    public void setProduct_color_types(String product_color_types) {
        this.product_color_types = product_color_types;
    }

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
    }

    public String getProduct_rotating_image() {
        return product_rotating_image;
    }

    public void setProduct_rotating_image(String product_rotating_image) {
        this.product_rotating_image = product_rotating_image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
