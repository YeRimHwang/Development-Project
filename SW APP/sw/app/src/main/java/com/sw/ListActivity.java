package com.sw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private ImageButton info, map, memo;

    private ViewPager vprMain;
    private FragmentAdapter adapter;
    private TextView texthigh;

    @Override
    public void onBackPressed() {
        if (adapter == null)
            return;
        if(adapter.getItem(vprMain.getCurrentItem())instanceof info){
            if (!((info) adapter.getItem(vprMain.getCurrentItem())).back()){
                super.onBackPressed(); // 첫 화면으로 돌아감
            }
        }
        else{
            super.onBackPressed();
        }
    }

    private void setViewpager() {
        adapter = new FragmentAdapter(getSupportFragmentManager(),1);
        vprMain.setAdapter(adapter);
        vprMain.setCurrentItem(0);
        vprMain.setOffscreenPageLimit(3);//주변에 있는 3개까지 저장
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        vprMain = findViewById(R.id.vpr_main);
        setViewpager();
        texthigh = findViewById(R.id.high);
        info = findViewById(R.id.info);
        map = findViewById(R.id.map);
        memo = findViewById(R.id.memo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texthigh.setText("   Information");
                vprMain.setCurrentItem(0);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texthigh.setText("   Map");
                vprMain.setCurrentItem(1);
                String fr2 = "Map";
                setText(fr2);
            }
            private void setText(String fr2) {
            }
        });
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texthigh.setText("   Memo");
                vprMain.setCurrentItem(2);
            }
        });
    }
}