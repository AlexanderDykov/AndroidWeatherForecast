package com.dux.weather_forecast.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dux.weather_forecast.R;
import com.dux.weather_forecast.model.WeatherViewModel;
import com.dux.weather_forecast.utils.Utility;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by DUX on 02.04.2016.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {


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
        return (position == 0) ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = -1;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.list_item_forecast_today;
                break;
            }
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item_forecast;
                break;
            }
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    public static <T> T checkNull(T obj, T defaultValue) {
        if (null == obj) return defaultValue;
        return obj;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        WeatherViewModel weatherViewModel = list.get(position);
        switch (getItemViewType(position)) {
            case VIEW_TYPE_TODAY:
                viewHolder.iconView.setImageResource(Utility.getArtResourceForWeatherCondition(
                        checkNull(weatherViewModel.getCondition(),0)));
                break;
            case VIEW_TYPE_FUTURE_DAY:
                viewHolder.iconView.setImageResource(Utility.getIconResourceForWeatherCondition(
                        checkNull(weatherViewModel.getCondition(),0)));
                break;
        }
        viewHolder.dateView.setText(Utility.getFriendlyDayString(context,checkNull(weatherViewModel.getDateTime(), 0L)));
        String description = checkNull(weatherViewModel.getDescription(),"");
        viewHolder.descriptionView.setText(description);
        viewHolder.iconView.setContentDescription(description);
        viewHolder.highTempView.setText(Utility.formatTemperature(context, checkNull(weatherViewModel.getHigh(),0D)));
        viewHolder.lowTempView.setText(Utility.formatTemperature(context, checkNull(weatherViewModel.getLow(),0D)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.list_item_icon)
        public ImageView iconView;
        @Bind(R.id.list_item_date_textview)
        public TextView dateView;
        @Bind(R.id.list_item_forecast_textview)
        public TextView descriptionView;
        @Bind(R.id.list_item_high_textview)
        public TextView highTempView;
        @Bind(R.id.list_item_low_textview)
        public TextView lowTempView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
