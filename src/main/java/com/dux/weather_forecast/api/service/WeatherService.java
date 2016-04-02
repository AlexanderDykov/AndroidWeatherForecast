package com.dux.weather_forecast.api.service;

import com.dux.weather_forecast.BuildConfig;
import com.dux.weather_forecast.api.ApiManager;
import com.dux.weather_forecast.api.interfaces.ApiService;
import com.dux.weather_forecast.api.interfaces.WeatherApi;
import com.dux.weather_forecast.api.interfaces.WeatherRepository;
import com.dux.weather_forecast.model.WeatherResponse;
import com.dux.weather_forecast.utils.Constants;

import rx.Observable;

/**
 * Created by DUX on 02.04.2016.
 */
public class WeatherService extends ApiManager implements ApiService, WeatherRepository {

    WeatherApi weatherApi;

    public WeatherService() {
        super(Constants.FORECAST_BASE_URL);
        this.weatherApi = getApiInterface(WeatherApi.class);
    }

    @Override
    public <T> T getApiInterface(Class<T> clazz) {
        return super.getApiInterface(clazz);
    }

    @Override
    public Observable<WeatherResponse> getWeather(String city) {
        return weatherApi.getWeather(BuildConfig.WEATHER_API_KEY, city);
    }
}
