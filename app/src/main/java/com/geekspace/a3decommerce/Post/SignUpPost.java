package com.geekspace.a3decommerce.Post;

public class SignUpPost {
    private String user_phone;
    private String user_password;
    private String user_passwordConfirm;

    public SignUpPost(String user_phone, String user_password, String user_passwordConfirm) {
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.user_passwordConfirm = user_passwordConfirm;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
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
