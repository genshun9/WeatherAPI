package com.example.genshun.apitest;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by genshun on 2016/03/27.
 */
public class AsyncHttpRequest extends AsyncTask<String, Void, String> {

    private Activity MainActivity;

    public AsyncHttpRequest(Activity activity) {

        // 呼び出し元のアクティビティ
        this.MainActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];

        try {
            URL restSearch = new URL(url);
            HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
            http.setRequestMethod("GET");
            http.connect();

            //Jackson
            ObjectMapper mapper = new ObjectMapper();
            viewJsonNode(mapper.readTree(http.getInputStream()));

        } catch (Exception e) {
            System.out.println(e);
            //TODO: 例外を考慮していません
        }

        return url;
    }

    private static void viewJsonNode(JsonNode nodeList) {
        System.out.println(nodeList);

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
                String category = r.path("category").asText();
                String url = r.path("url").asText();
                String address = r.path("address").asText();
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

    @Override
    protected void onPostExecute(String param) {
        // 取得した結果をテキストビューに入れちゃったり
        TextView textView = (TextView) this.MainActivity.findViewById(R.id.urlTextView);

        // TextViewの文字列をセット
        textView.setText(param);

        System.out.println("FINISH");

        return;
    }
}