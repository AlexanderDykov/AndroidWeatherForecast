package com.dux.weather_forecast.data.local;

import android.provider.BaseColumns;

/**
 * Created by DUX on 04.04.2016.
 */
public final class WeatherEntry implements BaseColumns {
    public static final String TABLE_NAME = "weather";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CONDITION = "condition";
    public static final String COLUMN_SHORT_DESC = "short_desc";
    public static final String COLUMN_MIN_TEMP = "min";
    public static final String COLUMN_MAX_TEMP = "max";
    public static final String COLUMN_HUMIDITY = "humidity";
    public static final String COLUMN_PRESSURE = "pressure";
    public static final String COLUMN_WIND_SPEED = "wind";
}
