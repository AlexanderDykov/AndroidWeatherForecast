package com.dux.weather_forecast.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dux.weather_forecast.BuildConfig;
import com.dux.weather_forecast.R;
import com.dux.weather_forecast.api.service.WeatherService;
import com.dux.weather_forecast.model.WeatherResponse;
import com.dux.weather_forecast.model.WeatherViewModel;
import com.dux.weather_forecast.ui.adapter.WeatherListAdapter;
import com.dux.weather_forecast.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DUX on 02.04.2016.
 */
public class ForecastFragment  extends Fragment {

    WeatherService weatherService;
    @Bind(R.id.listview_forecast)RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        weatherService = new WeatherService();
        ArrayList<WeatherViewModel> weatherViewModels = new ArrayList<>();
        WeatherViewModel weatherViewModel = new WeatherViewModel();
        weatherViewModel.setType(0);
        weatherViewModels.add(weatherViewModel);
        weatherViewModel = new WeatherViewModel();
        weatherViewModel.setType(1);
        weatherViewModels.add(weatherViewModel);
        weatherViewModel = new WeatherViewModel();
        weatherViewModel.setType(1);
        weatherViewModels.add(weatherViewModel);
        weatherViewModel = new WeatherViewModel();
        weatherViewModel.setType(1);
        weatherViewModels.add(weatherViewModel);
        WeatherListAdapter weatherListAdapter = new WeatherListAdapter(this.getContext(),weatherViewModels);
        recyclerView.setAdapter(weatherListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh){
            weatherService.getWeather("Zhytomyr").subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherResponse>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("Test", e.getMessage());
                        }

                        @Override
                        public final void onNext(WeatherResponse response) {

                        }
                    });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
