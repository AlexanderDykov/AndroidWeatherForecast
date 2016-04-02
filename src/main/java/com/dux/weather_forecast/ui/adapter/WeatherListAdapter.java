package com.dux.weather_forecast.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dux.weather_forecast.R;
import com.dux.weather_forecast.model.WeatherViewModel;

import java.util.ArrayList;

/**
 * Created by DUX on 02.04.2016.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;
    Context context;
    ArrayList<WeatherViewModel> list;

    public WeatherListAdapter(Context context, ArrayList<WeatherViewModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.list_item_forecast_today;
                View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
                return new ViewHolderToday(view);
            }
            default:
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item_forecast;
                View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
                return new ViewHolderFutureDay(view);
            }

        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_TODAY:
                ViewHolderToday viewHolderFirst = (ViewHolderToday)holder;
                break;
            case VIEW_TYPE_FUTURE_DAY:
                ViewHolderFutureDay viewHolderSecond = (ViewHolderFutureDay)holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderToday extends RecyclerView.ViewHolder{

        public ViewHolderToday(View itemView) {
            super(itemView);
        }

    }

    public class ViewHolderFutureDay extends RecyclerView.ViewHolder{

        public ViewHolderFutureDay(View itemView) {
            super(itemView);
        }

    }
}
