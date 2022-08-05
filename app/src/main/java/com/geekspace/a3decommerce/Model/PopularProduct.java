package com.geekspace.a3decommerce.Model;

import java.util.ArrayList;

public class PopularProduct {
    private PopularData data;

    public PopularProduct(PopularData data) {
        this.data = data;
    }

    public PopularData getData() {
        return data;
    }

    public void setData(PopularData data) {
        this.data = data;
    }
}
