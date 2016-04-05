package com.dux.weather_forecast.data.remote.service;

import android.text.format.Time;

import com.dux.weather_forecast.BuildConfig;
import com.dux.weather_forecast.data.remote.ApiManager;
import com.dux.weather_forecast.data.remote.interfaces.WeatherApi;
import com.dux.weather_forecast.data.WeatherRepository;
import com.dux.weather_forecast.model.ResponseType;
import com.dux.weather_forecast.model.WeatherViewModel;
import com.dux.weather_forecast.model.response.Main;
import com.dux.weather_forecast.model.response.Temp;
import com.dux.weather_forecast.model.response.Weather;
import com.dux.weather_forecast.model.response.WeatherResponse;
import com.dux.weather_forecast.utils.Constants;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by DUX on 02.04.2016.
 */
public class ApiService extends ApiManager implements  WeatherRepository {

    WeatherApi weatherApi;
    public final String units = "metric";
    public final String mode = "json";

    public ApiService() {
        super(Constants.FORECAST_BASE_URL);
        this.weatherApi = getApiInterface(WeatherApi.class);
    }


    @Override
    public Observable<ArrayList<WeatherViewModel>> getWeather(String city) {
        return weatherApi.getWeather(BuildConfig.WEATHER_API_KEY, city,units,mode)
                .map(new Func1<WeatherResponse, ArrayList<WeatherViewModel>>() {
                    @Override
                    public ArrayList<WeatherViewModel> call(WeatherResponse weatherResponse) {
                        return weatherResponse != null ? parseResponse(weatherResponse): null;
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private ArrayList<WeatherViewModel> parseResponse(WeatherResponse response) {
        ArrayList<WeatherViewModel> list = new ArrayList<>();
        Time dayTime = new Time();
        dayTime.setToNow();
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
        dayTime = new Time();
        for (int i = 0; i < response.getList().size(); i++) {
            WeatherViewModel weatherViewModel = new WeatherViewModel();
            weatherViewModel.setLocation(response.getCity().getName());
            Main dayForecast = response.getList().get(i);
            weatherViewModel.setDateTime(dayTime.setJulianDay(julianStartDay + i));
            weatherViewModel.setPressure(dayForecast.getPressure());
            weatherViewModel.setHumidity(dayForecast.getHumidity());
            weatherViewModel.setWindSpeed(dayForecast.getSpeed());
            weatherViewModel.setWindDirection(dayForecast.getDt());
            Weather weatherObject = response.getList().get(i).getWeather().get(0);
            weatherViewModel.setDescription(weatherObject.getMain());
            weatherViewModel.setCondition(weatherObject.getId());
            Temp temperatureObject = dayForecast.getTemp();
            weatherViewModel.setHigh(temperatureObject.getMax());
            weatherViewModel.setLow(temperatureObject.getMin());
            list.add(weatherViewModel);
        }
        return list;
    }


}
