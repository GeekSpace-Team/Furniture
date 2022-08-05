package com.geekspace.a3decommerce.Api;

import com.geekspace.a3decommerce.Model.Category;
import com.geekspace.a3decommerce.Model.CreateAccountPost;
import com.geekspace.a3decommerce.Model.CreateAccountResponse;
import com.geekspace.a3decommerce.Model.GetHome;
import com.geekspace.a3decommerce.Model.PopularProduct;
import com.geekspace.a3decommerce.Model.SignUpResponse;
import com.geekspace.a3decommerce.Model.SingleProduct;
import com.geekspace.a3decommerce.Post.SignUpPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/public/home")
    Call<GetHome> getHome();

    @GET("/public/products/popular")
    Call<PopularProduct> getPopularProducts();

    @GET("/public/products/{id}")
    Call<SingleProduct> getSingleProduct(@Path("id") String id);

    @GET("/public/categories")
    Call<ArrayList<Category>> getAllCategories();

    @POST("/users/signup")
    Call<SignUpResponse> signUp(@Body SignUpPost post);

    @POST("/users/signup")
    Call<CreateAccountResponse> createAccount(@Body CreateAccountPost post);
}
