package com.example.dell.socialsift.utils.networking;


import com.example.dell.socialsift.beans.LoginBean;
import com.example.dell.socialsift.beans.bodyBean.RegistrationBean;
import com.example.dell.socialsift.beans.brandBean.CreateBrandBean;
import com.example.dell.socialsift.beans.brandBean.MyBrandList;
import com.example.dell.socialsift.beans.brandBean.ParticularBrandBean;
import com.example.dell.socialsift.beans.brandBean.RandomBrandList;
import com.example.dell.socialsift.beans.brandBean.SendPostBean;
import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
import com.example.dell.socialsift.beans.masterBean.MasterDataBean;
import com.example.dell.socialsift.beans.profileBean.GetUserDashboardBean;
import com.example.dell.socialsift.beans.profileBean.GetUserDetailsBean;
import com.example.dell.socialsift.beans.profileBean.UpdateProfileBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    /*LOGIN-REGISTERATION*/

    //login
    @POST("oauth/token")
    Call<LoginBean> getLoginInfo(
            @Query("username") String uName,
            @Query("password") String pass,
            @Query("grant_type") String type
    );

    //refresh token
    @POST("oauth/token")
    Call<LoginBean> getRefreshToken(
            @Query("grant_type") String type,
            @Query("refresh_token") String refreshToken
    );

    //masterdata
    @GET("masterdata")
    Call<MasterDataBean> getMasterData();

    @POST("register")
    Call<ResponseMessage> callRegistration(@Body RegistrationBean bean);

    @GET("profile")
    Call<GetUserDetailsBean> getProfileData();

    @PUT("profile")
    Call<ResponseMessage> updateProfileData(@Body UpdateProfileBean bean);

    @PUT("password")
    Call<ResponseMessageWrapper> changePassword(@Body HashMap<String, String> parameters);

    @POST("brand")
    Call<ResponseMessageWrapper> callBrandCreation(@Body CreateBrandBean bean);

    @GET("brand/randomBrands")
    Call<RandomBrandList> callForGetRandomList();

    @GET("brand/subscribed")
    Call<RandomBrandList> callForGetSubscribeList();

    @GET("brand/created")
    Call<MyBrandList> callForGetBrandList();

    @GET("brand/{Id}")
    Call<ParticularBrandBean> getBrandDetails(@Path("Id") String customerId);

    @PUT("brand/subscribe/{Id}")
    Call<ResponseMessageWrapper> subscribeBrand(@Path("Id") String customerId);

    @DELETE("brand/unsubscribe/{Id}")
    Call<ResponseMessageWrapper> unsubscribeBrand(@Path("Id") String customerId);

    @POST("brand/active/{Id}")
    Call<ResponseMessageWrapper> activateBrand(@Path("Id") String customerId);

    @DELETE("brand/deactive/{Id}")
    Call<ResponseMessageWrapper> deActivateBrand(@Path("Id") String customerId);

    @GET("getDashboard")
    Call<GetUserDashboardBean> getUserDetails();


    //search by category
    @POST("category/{Id}")
    Call<RandomBrandList> getDataByCategory(@Path("Id") String categoryId);

    @GET("brand/{Id}")
    Call<ResponseMessageWrapper> updateBrand(@Path("Id") String customerId, @Body HashMap<String, String> parameters);

//Post on brand

    @POST("brand/post/{Id}")
    Call<ResponseMessageWrapper> postOnBrand(@Path("Id") String brandId, @Body SendPostBean bean);
}
