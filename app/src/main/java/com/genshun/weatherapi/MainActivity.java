package com.genshun.weatherapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String END_POINT = "http://weather.livedoor.com";

    private static final String TAG = MainActivity.class.getSimpleName();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        // RestAdapterを作成する
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(END_POINT)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();

        // 天気予報情報を取得する
        //http://weather.livedoor.com/area/forecast/200010
        WeatherApi api =  adapter.create(WeatherApi.class);

        Observer observer = new Observer<WeatherEntity>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
                //必要な情報を取り出して画面に表示してください。
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error : " + e.toString());
            }

            @Override
            public void onNext(WeatherEntity weather) {
                Log.d(TAG, "onNext()");
            }
        };

        api.getWeather("200010")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}