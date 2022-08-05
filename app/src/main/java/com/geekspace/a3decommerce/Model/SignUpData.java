package com.geekspace.a3decommerce.Model;

public class SignUpData {
    private SignUpUser user;

    public SignUpData(SignUpUser user) {
        this.user = user;
    }

    public SignUpUser getUser() {
        return user;
    }

    public void setUser(SignUpUser user) {
        this.user = user;
    }
}
