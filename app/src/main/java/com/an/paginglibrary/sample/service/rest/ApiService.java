package com.an.paginglibrary.sample.service.rest;

import com.an.paginglibrary.sample.notification.NotificationDTO;
import com.an.paginglibrary.sample.service.dto.ResponseDTO;
import com.an.paginglibrary.sample.service.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Restful Services
 * Created by hungdn on 8/07/2016.
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("/admin/login")
    Call<ResponseDTO<UserDTO>> login(@Field("data[User][username]") String channel,
                                     @Field("data[User][password]") String password);

    @GET("/admin/AdEmployeeNotifications/getNotifications")
    Call<ResponseDTO<List<NotificationDTO>>> getNotification(@Query("page") long page);

}