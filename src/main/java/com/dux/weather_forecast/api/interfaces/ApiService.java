package com.dux.weather_forecast.api.interfaces;

/**
 * Created by DUX on 02.04.2016.
 */
public interface ApiService {
    <T> T getApiInterface(Class<T> clazz);
}
