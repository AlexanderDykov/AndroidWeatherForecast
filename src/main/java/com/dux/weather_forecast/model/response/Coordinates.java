package com.dux.weather_forecast.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DUX on 02.04.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
