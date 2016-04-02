package com.dux.weather_forecast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by DUX on 02.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private City city;
    private Integer cod;
    private Double message;
    private Integer cnt;
    private ArrayList<Main> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public ArrayList<Main> getList() {
        return list;
    }

    public void setList(ArrayList<Main> list) {
        this.list = list;
    }
}
