package com.geekspace.a3decommerce.Model;

public class CreateAccountPost {
    private String user_checked_phone;
    private String user_password;
    private String user_passwordConfirm;

    public CreateAccountPost(String user_checked_phone, String user_password, String user_passwordConfirm) {
        this.user_checked_phone = user_checked_phone;
        this.user_password = user_password;
        this.user_passwordConfirm = user_passwordConfirm;
    }

    public String getUser_checked_phone() {
        return user_checked_phone;
    }

    public void setUser_checked_phone(String user_checked_phone) {
        this.user_checked_phone = user_checked_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_passwordConfirm() {
        return user_passwordConfirm;
    }

    public void setUser_passwordConfirm(String user_passwordConfirm) {
        this.user_passwordConfirm = user_passwordConfirm;
    }
}
