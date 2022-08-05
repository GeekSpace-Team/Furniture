package com.geekspace.a3decommerce.Model;

public class GetHome {
    private HomeData data;

    public GetHome(HomeData data) {
        this.data = data;
    }

    public HomeData getData() {
        return data;
    }

    public void setData(HomeData data) {
        this.data = data;
    }
}
