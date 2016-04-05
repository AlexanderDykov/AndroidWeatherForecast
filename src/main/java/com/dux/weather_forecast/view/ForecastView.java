package com.dux.weather_forecast.view;

import com.dux.weather_forecast.model.WeatherViewModel;

import java.util.ArrayList;

/**
 * Created by DUX on 05.04.2016.
 */
public interface ForecastView {
    void onLoad(ArrayList<WeatherViewModel> weatherViewModels);
    void onRefresh(ArrayList<WeatherViewModel> weatherViewModels);
}
