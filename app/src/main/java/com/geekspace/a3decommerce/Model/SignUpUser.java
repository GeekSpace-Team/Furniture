package com.geekspace.a3decommerce.Model;

public class SignUpUser {
    private String user_id;
    private String id;
    private String user_phone;
    private String updatedAt;
    private String createdAt;
    private String user_name;
    private String user_address;

    public SignUpUser(String user_id, String id, String user_phone, String updatedAt, String createdAt, String user_name, String user_address) {
        this.user_id = user_id;
        this.id = id;
        this.user_phone = user_phone;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.user_name = user_name;
        this.user_address = user_address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
