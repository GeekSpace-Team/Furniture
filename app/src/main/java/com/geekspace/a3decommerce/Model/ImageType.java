package com.geekspace.a3decommerce.Model;

public class ImageType {
    private int id;
    private String imageUrl;
    private String type;

    public ImageType(int id, String imageUrl, String type) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
