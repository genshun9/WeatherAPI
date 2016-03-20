package com.genshun.weatherapi;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by genshun on 16/03/20.
 */
public interface WeatherApi {

    @GET("/forecast/webservice/json/v1")
    public Observable<WeatherEntity> getWeather(@Query("city") final String city);
}
