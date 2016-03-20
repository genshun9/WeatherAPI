package com.genshun.weatherapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by genshun on 16/03/20.
 */
public class WeatherEntity {
    @Expose
    @SerializedName("pinpointLocations")
    private List<PinpointLocations> pinpointLocations;

    @Expose
    @SerializedName("forecasts")
    private List<Forecasts> forecasts;

    @Expose
    @SerializedName("link")
    private String link;

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecasts> forecasts) {
        this.forecasts = forecasts;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PinpointLocations> getPinpointLocations() {
        return pinpointLocations;
    }

    public void setPinpointLocations(List<PinpointLocations> pinpointLocations) {
        this.pinpointLocations = pinpointLocations;
    }
}
