package com.example.genshun.apitest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void getGurunavi(View view) {
        final TextView tv = (TextView) findViewById(R.id.myTextView);
        String result1 = "getGurunavi";
        tv.setText(result1);

        if (view.getId() == R.id.buttonA) {
            // アクセスキー
            String acckey = "b888022eaa8dc2f876922c16ae577e28";
            // 住所
            String address = "106-0032";
            // ランチ
            String lunch = "1";
            // 範囲
//            String range = "1";
            // 返却形式
            String format = "json";
            // エンドポイント
            String gnaviRestUri = "http://api.gnavi.co.jp/RestSearchAPI/20150630/";
            String prmFormat = "?format=" + format;
            String prmKeyid = "&keyid=" + acckey;
            String prmAddress = "&address=" + address;
            String prmLunch = "&lunch=" + lunch;
//            String prmRange = "&range=" + range;

            // URI組み立て
            StringBuffer uri = new StringBuffer();
            uri.append(gnaviRestUri);
            uri.append(prmFormat);
            uri.append(prmKeyid);
            uri.append(prmAddress);
            uri.append(prmLunch);
//            uri.append(prmRange);

            String url = uri.toString();

            AsyncHttpRequest task = new AsyncHttpRequest() {
                @Override
                protected void onPostExecute(String param) {
                    // 取得した結果をテキストビューに入れちゃったり
                    System.out.println("ONPOST");
                    System.out.println(param);
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("param", param);
                    startActivity(intent);
                }
            };
            task.execute(url);
        }

    }
}
