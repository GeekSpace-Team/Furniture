package com.geekspace.a3decommerce.Model;

public class Banner {
    private int id;
    private String banner_id;
    private String banner_image;
    private String banner_product_id;
    private String banner_url;
    private String createdAt;
    private String updatedAt;

    public Banner(int id, String banner_id, String banner_image, String banner_product_id, String banner_url, String createdAt, String updatedAt) {
        this.id = id;
        this.banner_id = banner_id;
        this.banner_image = banner_image;
        this.banner_product_id = banner_product_id;
        this.banner_url = banner_url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getBanner_product_id() {
        return banner_product_id;
    }

    public void setBanner_product_id(String banner_product_id) {
        this.banner_product_id = banner_product_id;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
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
