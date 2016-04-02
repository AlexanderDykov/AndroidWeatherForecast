package com.dux.weather_forecast.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.JacksonConverterFactory;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.Retrofit;

/**
 * Created by DUX on 02.04.2016.
 */
public class ApiManager  {

    private Retrofit retrofit;
    private final String API_ENDPOINT;

    public ApiManager(String baseUrl){

        this.API_ENDPOINT = baseUrl;
        this.retrofit = getRetrofit();

    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(this.API_ENDPOINT)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public  <T> T getApiInterface(Class<T> clazz){
        return  this.retrofit.create(clazz);
    }
}
