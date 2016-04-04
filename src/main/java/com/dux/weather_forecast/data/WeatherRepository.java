package com.dux.weather_forecast.data;

import com.dux.weather_forecast.model.WeatherViewModel;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by DUX on 02.04.2016.
 */
public interface WeatherRepository {
    Observable<ArrayList<WeatherViewModel>> getWeather(String city);
}
