package com.geekspace.a3decommerce.Model;

public class CreateAccountResponse {
    private String token;
    private SignUpData data;

    public CreateAccountResponse(String token, SignUpData data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignUpData getData() {
        return data;
    }

    public void setData(SignUpData data) {
        this.data = data;
    }
}
