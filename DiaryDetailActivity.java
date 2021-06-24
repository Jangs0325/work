package com.swufe.work;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


//AppCompatActicity主界面带有toolbar的标题栏
public class DiaryDetailActivity extends AppCompatActivity {
    private TextView tv_diarydetail_content;
    private TextView tv_diarydetail_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        tv_diarydetail_title.setText(intent.getStringExtra("title"));
        tv_diarydetail_content.setText(intent.getStringExtra("content"));
    }

    private void initView() {
        tv_diarydetail_title = findViewById(R.id.tv_diarydetail_title);
        tv_diarydetail_content = findViewById(R.id.tv_diarydetail_content);
    }
}
