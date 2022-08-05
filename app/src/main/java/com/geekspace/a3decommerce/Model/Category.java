package com.geekspace.a3decommerce.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
    int id;
    String category_id;
    @SerializedName("category_image")
    String image;
    @SerializedName("category_name_tm")
    String name;
    String category_name_ru;
    String createdAt;
    String updatedAt;

    public Category(int id, String category_id, String image, String name, String category_name_ru, String createdAt, String updatedAt) {
        this.id = id;
        this.category_id = category_id;
        this.image = image;
        this.name = name;
        this.category_name_ru = category_name_ru;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_name_ru() {
        return category_name_ru;
    }

    public void setCategory_name_ru(String category_name_ru) {
        this.category_name_ru = category_name_ru;
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
