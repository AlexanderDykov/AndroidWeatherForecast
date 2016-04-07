package com.dux.weather_forecast.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dux.weather_forecast.R;
import com.dux.weather_forecast.model.WeatherViewModel;
import com.dux.weather_forecast.presenter.ForecastPresenter;
import com.dux.weather_forecast.ui.adapter.WeatherListAdapter;
import com.dux.weather_forecast.view.ForecastView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by DUX on 02.04.2016.
 */
public class ForecastFragment extends Fragment implements ForecastView {

    @Bind(R.id.listview_forecast)
    RecyclerView recyclerView;
    WeatherListAdapter weatherListAdapter;
    Context context;
    ArrayList<WeatherViewModel> list;
    ForecastPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        context = this.getActivity();
        presenter = new ForecastPresenter(context, this);
        presenter.loadData();
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            presenter.refreshData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoad(ArrayList<WeatherViewModel> weatherViewModels) {
        list = weatherViewModels;
        weatherListAdapter = new WeatherListAdapter(context, list);
        recyclerView.setAdapter(weatherListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onRefresh(ArrayList<WeatherViewModel> weatherViewModels) {
        list = weatherViewModels;
        weatherListAdapter.notifyItemRangeChanged(0, list.size());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.destroy();
    }
}
