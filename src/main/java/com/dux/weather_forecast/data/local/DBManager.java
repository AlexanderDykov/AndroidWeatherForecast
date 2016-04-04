package com.dux.weather_forecast.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dux.weather_forecast.data.WeatherRepository;
import com.dux.weather_forecast.model.WeatherViewModel;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by DUX on 04.04.2016.
 */
public class DBManager implements WeatherRepository {
    SQLiteDatabase db;

    public DBManager(Context context) {
        WeatherDbHelper helper = new WeatherDbHelper(context);
        db = helper.getWritableDatabase();

    }

    public void insertData(ArrayList<WeatherViewModel> list) {
        for (int i = 0; i < list.size(); i++) {
            WeatherViewModel weatherViewModel = list.get(i);
            ContentValues weatherValues = new ContentValues();
            weatherValues.put(WeatherEntry.COLUMN_LOCATION, weatherViewModel.getLocation());
            weatherValues.put(WeatherEntry.COLUMN_DATE, weatherViewModel.getDateTime());
            weatherValues.put(WeatherEntry.COLUMN_HUMIDITY, weatherViewModel.getHumidity());
            weatherValues.put(WeatherEntry.COLUMN_PRESSURE, weatherViewModel.getPressure());
            weatherValues.put(WeatherEntry.COLUMN_WIND_SPEED, weatherViewModel.getWindSpeed());
            weatherValues.put(WeatherEntry.COLUMN_MAX_TEMP, weatherViewModel.getHigh());
            weatherValues.put(WeatherEntry.COLUMN_MIN_TEMP, weatherViewModel.getLow());
            weatherValues.put(WeatherEntry.COLUMN_SHORT_DESC, weatherViewModel.getDescription());
            weatherValues.put(WeatherEntry.COLUMN_CONDITION, weatherViewModel.getCondition());
            db.insert(WeatherEntry.TABLE_NAME, null, weatherValues);
        }
    }

    public ArrayList<WeatherViewModel> getData() {
        Cursor cursor = db.query(WeatherEntry.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<WeatherViewModel> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int locationIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_LOCATION);
            int dateIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_DATE);
            int humIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_HUMIDITY);
            int preIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_PRESSURE);
            int windIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_WIND_SPEED);
            int maxIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_MAX_TEMP);
            int minIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_MIN_TEMP);
            int descIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_SHORT_DESC);
            int condIndex = cursor.getColumnIndex(WeatherEntry.COLUMN_CONDITION);

            do {
                WeatherViewModel weatherViewModel = new WeatherViewModel();
                weatherViewModel.setLocation(cursor.getString(locationIndex));
                weatherViewModel.setDescription(cursor.getString(descIndex));
                weatherViewModel.setCondition(cursor.getInt(condIndex));
                weatherViewModel.setDateTime(cursor.getLong(dateIndex));
                weatherViewModel.setHigh(cursor.getDouble(maxIndex));
                weatherViewModel.setLow(cursor.getDouble(minIndex));
                weatherViewModel.setWindSpeed(cursor.getDouble(windIndex));
                weatherViewModel.setPressure(cursor.getDouble(preIndex));
                weatherViewModel.setHumidity(cursor.getInt(humIndex));
                list.add(weatherViewModel);
            } while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");

        cursor.close();
        return list;
    }

    public void deleteData() {
        db.delete(WeatherEntry.TABLE_NAME, null, null);
    }

    @Override
    public Observable<ArrayList<WeatherViewModel>> getWeather(String city) {
        return Observable.just(getData());
    }
}
