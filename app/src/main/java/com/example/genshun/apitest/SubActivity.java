package com.example.genshun.apitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by genshun on 2016/03/29.
 */
public class SubActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sub);

        Button btn = (Button) findViewById(R.id.buttonB);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                finish();
            }
        });

        String param = getIntent().getStringExtra("param");
        TextView tv = (TextView) this.findViewById(R.id.result);
        tv.setText(param);
    }
}
