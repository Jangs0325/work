package com.swufe.work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DiaryActivity extends AppCompatActivity {
    private TextView tvDiaryCancel;
    private TextView tvDiarySave;
    private EditText etDiaryTitle;
    private EditText etDiaryContent;
    private LocalBroadcastManager mLBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        mLBM=LocalBroadcastManager.getInstance(DiaryActivity.this);//得到系统当前已经实例化的该类对象，若当前系统还没有实例化过这个类的对象，则调用此类的构造函数
        initView();
        initListener();
    }

    private void initListener() {
        tvDiaryCancel.setOnClickListener(new View.OnClickListener() {//监听
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        tvDiarySave.setOnClickListener(new View.OnClickListener() {//监听
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        String title = etDiaryTitle.getText().toString();
        String content = etDiaryContent.getText().toString();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd HH:mm");//日期格式化
        Date time = new Date(System.currentTimeMillis());//获得当前时间
        String date=simpleDateFormat.format(time);
        Diaryinfo diaryinfo=new Diaryinfo();
        diaryinfo.setTitle(title);
        diaryinfo.setContent(content);
        diaryinfo.setDate(date);
        Model.getInstance().getDiaryTable2().saveDiary(diaryinfo);
        mLBM.sendBroadcast(new Intent("diary_change"));
        finish();
    }

    private void cancel() {
        Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
        startActivity(intent);
    }
//读
    private void initView() {
        tvDiaryCancel = (TextView)findViewById( R.id.tv_diary_cancel );
        tvDiarySave = (TextView)findViewById( R.id.tv_diary_save );
        etDiaryTitle = (EditText)findViewById( R.id.et_diary_title );
        etDiaryContent = (EditText)findViewById( R.id.et_diary_content );
    }
}


//部分代码借鉴https://blog.csdn.net/DieForCode/article/details/90602224?utm_source=app&app_version=4.9.1&code=app_1562916241&uLinkId=usr1mkqgl919blen
//和SQL数据库链接方面另外参考了https://github.com/laoyingyong/NotePad
//https://blog.csdn.net/bingocoder/article/details/80784802?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162420057916780366595590%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=162420057916780366595590&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-5-80784802.pc_search_result_cache&utm_term=Android%E8%AE%B0%E4%BA%8B%E6%9C%AC%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BF%9D%E5%AD%98&spm=1018.2226.3001.4187
