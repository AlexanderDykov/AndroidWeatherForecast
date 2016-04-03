package com.dux.weather_forecast.api.interfaces;

import com.dux.weather_forecast.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DUX on 02.04.2016.
 */
public interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    Observable<WeatherResponse> getWeather(@Query("appid") String key, @Query("q") String cityName, @Query("units") String units, @Query("mode") String mode);
}
