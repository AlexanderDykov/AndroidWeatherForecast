package com.dux.weather_forecast.api.interfaces;

import com.dux.weather_forecast.model.WeatherResponse;

import rx.Observable;

/**
 * Created by DUX on 02.04.2016.
 */
public interface WeatherRepository {
    Observable<WeatherResponse> getWeather(String city);
}
