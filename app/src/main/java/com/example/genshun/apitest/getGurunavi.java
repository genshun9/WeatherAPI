package com.example.genshun.apitest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by genshun on 2016/03/22.
 */
public class getGurunavi {

    //コンパイルが通ったOkHttpコード
//    private String getGurunavi() throws IOException {
//        Request request = new Request.Builder()
//                .url("http://api.gnavi.co.jp/RestSearchAPI/20150630/?keyid=c5ce4417cf026cf0635428cf0e6fa8c3&format=json&address=106-0032&lunch=1")
//                .get()
//                .build();
//
//        OkHttpClient client = new OkHttpClient();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }


    public static void main(String[] args) {
        // アクセスキー
        String acckey = "c5ce4417cf026cf0635428cf0e6fa8c3";
        // 住所
        String address = "106-0032";
        // ランチ
        String lunch = "1";
        // 範囲
        String range = "1";
        // 返却形式
        String format = "json";
        // エンドポイント
        String gnaviRestUri = "http://api.gnavi.co.jp/RestSearchAPI/20150630/";
        String prmFormat = "?format=" + format;
        String prmKeyid = "&keyid=" + acckey;
        String prmAddress = "&address=" + address;
        String prmLunch = "&lunch=" + lunch;
        String prmRange = "&range=" + range;

        // URI組み立て
        StringBuffer uri = new StringBuffer();
        uri.append(gnaviRestUri);
        uri.append(prmFormat);
        uri.append(prmKeyid);
        uri.append(prmAddress);
        uri.append(prmLunch);
        uri.append(prmRange);

        // API実行、結果を取得し出力
        getNodeList(uri.toString());

    }

    private static void getNodeList(String url) {
        try {
            URL restSearch = new URL(url);
            HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            //Jackson
            ObjectMapper mapper = new ObjectMapper();
            viewJsonNode(mapper.readTree(http.getInputStream()));

        } catch (Exception e) {
            //TODO: 例外を考慮していません
        }

    }

    private static void viewJsonNode(JsonNode nodeList) {
        if (nodeList != null) {
            //トータルヒット件数
            String hitcount = "total:" + nodeList.path("total_hit_count").asText();
            System.out.println(hitcount);
            //restのみ取得
            JsonNode restList = nodeList.path("rest");
            Iterator<JsonNode> rest = restList.iterator();
            //店舗番号、店舗名、最寄の路線、最寄の駅、最寄駅から店までの時間、店舗の小業態を出力
            while (rest.hasNext()) {
                JsonNode r = rest.next();
                String id = r.path("id").asText();
                String name = r.path("name").asText();
                String line = r.path("access").path("line").asText();
                String station = r.path("access").path("station").asText();
                String walk = r.path("access").path("walk").asText() + "分";
                String categorys = "";

                for (JsonNode n : r.path("code").path("category_name_s")) {
                    categorys += n.asText();
                }
                System.out.println(id + "¥t" + name + "¥t" + line + "¥t" + station + "¥t" + walk + "¥t" + categorys);
            }
        }

    }
}
