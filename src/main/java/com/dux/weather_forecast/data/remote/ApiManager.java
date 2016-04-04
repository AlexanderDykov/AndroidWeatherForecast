package com.dux.weather_forecast.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.JacksonConverterFactory;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.Retrofit;

/**
 * Created by DUX on 02.04.2016.
 */
public class ApiManager  {

    private final int CONNECT_TIMEOUT = 15;
    private final int WRITE_TIMEOUT = 60;
    private final int TIMEOUT = 60;

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private final String API_ENDPOINT;
    private OkHttpClient.Builder okHttpClientBuilder;
    public ApiManager(String baseUrl){

        this.API_ENDPOINT = baseUrl;
        this.okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS);

        this.okHttpClient = this.okHttpClientBuilder.build();
        this.retrofit = getRetrofit(okHttpClient);

    }

    private Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(this.API_ENDPOINT)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public  <T> T getApiInterface(Class<T> clazz){
        return  this.retrofit.create(clazz);
    }
}
