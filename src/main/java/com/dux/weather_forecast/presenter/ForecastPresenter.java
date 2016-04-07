package com.dux.weather_forecast.presenter;

import android.content.Context;

import com.dux.weather_forecast.data.local.CacheService;
import com.dux.weather_forecast.data.remote.service.ApiService;
import com.dux.weather_forecast.model.WeatherViewModel;
import com.dux.weather_forecast.view.ForecastView;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by DUX on 05.04.2016.
 */
public class ForecastPresenter {
    ForecastView view;
    CacheService cacheService;
    ApiService apiService;

    public ForecastPresenter(Context context, ForecastView view) {
        this.view = view;
        apiService = new ApiService();
        cacheService = new CacheService(context);
    }

    public void loadData() {
        apiService.getWeather("Zhytomyr")
                .startWith(cacheService.getWeather("Zhytomyr"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<ArrayList<WeatherViewModel>>() {
                    @Override
                    public void call(ArrayList<WeatherViewModel> weatherViewModels) {
                        if (!weatherViewModels.isEmpty()) {
                            if (!weatherViewModels.get(0).getDateTime().equals(cacheService.getFirstDate()))
                                cacheService.updateData(weatherViewModels);
                            view.onLoad(weatherViewModels);
                        }
                    }
                })
                .onErrorReturn(new Func1<Throwable, ArrayList<WeatherViewModel>>() {
                    @Override
                    public ArrayList<WeatherViewModel> call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe();
    }

    public void refreshData() {
        apiService.getWeather("Zhytomyr")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<ArrayList<WeatherViewModel>>() {
                    @Override
                    public void call(ArrayList<WeatherViewModel> weatherViewModels) {
                        if (!weatherViewModels.isEmpty()) {
                            if (!weatherViewModels.get(0).getDateTime().equals(cacheService.getFirstDate()))
                                cacheService.updateData(weatherViewModels);
                            view.onRefresh(weatherViewModels);
                        }
                    }
                })
                .onErrorReturn(new Func1<Throwable, ArrayList<WeatherViewModel>>() {
                    @Override
                    public ArrayList<WeatherViewModel> call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe();
    }
}
