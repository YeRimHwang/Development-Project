package com.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Intent intent = getIntent();

        TextView textv1;
        String re1 = intent.getExtras().getString("company");
        textv1 = (TextView)findViewById(R.id.text2);
        textv1.setText(re1);

        TextView textv2;
        String re2 = intent.getExtras().getString("user");
        textv2 = (TextView)findViewById(R.id.text1);
        textv2.setText(re2);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity2.this,ListActivity.class);

                startActivity(i);
                finish();
            }
        },1500);

    }
}