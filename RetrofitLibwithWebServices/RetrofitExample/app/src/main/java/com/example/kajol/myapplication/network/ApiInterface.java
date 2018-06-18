package com.example.kajol.myapplication.network;

import com.example.kajol.myapplication.AppConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kajol on 3/22/2017.
 */
public interface ApiInterface {
// success

    @FormUrlEncoded
    @POST(AppConfig.URL_LOGIN)
    Call<Login> LOGIN_CALL(@Field("email") String email
            , @Field("password") String password);

    @FormUrlEncoded
    @POST(AppConfig.URL_REGISTER)
    Call<Register> REGISTER_CALL(@Field("name") String name, @Field("email") String email
            , @Field("password") String password, @Field("isBuyer") int isBuyer, @Field("isSeller") int isSeller);

    @FormUrlEncoded
    @POST(AppConfig.URL_PRODUCTS)
    Call<StoreProduct> PRODUCT_STORE_CALL(@Field("UniqueProductId") String UniqueProductId, @Field("name") String name, @Field("email") String email
            , @Field("productname") String productname);

    @FormUrlEncoded
    @POST(AppConfig.URL_DISPLAY_PRODUCTS)
    Call<ProductDisplay> PRODUCT_DISPLAY_CALL(@Field("email") String email);

    @FormUrlEncoded
    @POST(AppConfig.URL_UPDATE_PRODUCTS)
    Call<UpdateProduct> PRODUCT_UPDATE_CALL(@Field("Newproductname") String Newproductname, @Field("UniqueProductId") String UniqueProductId);

}
