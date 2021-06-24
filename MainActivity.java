package com.swufe.work;

import android.content.BroadcastReceiver;//广播接收系统，接受网络返回的数据
import android.content.Context;//弹出Toast、启动Activity、启动Service、发送广播、操作数据库等等等等都需要用到Context
import android.content.Intent;//指明当前组件想要执行的动作，还可以在不同组件之间传递数据。
import android.content.IntentFilter;// 当Intent在组件间传递时，组件如果想告知Android系统自己能够响应和处理哪些Intent，那么就需要用到IntentFilter对象。
import android.os.Bundle;//两个activity之间的通讯可以通过bundle类来实现
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv_main_diary;//内容
    private ImageView iv_main_write;//显示图像资源
    private DiaryListAdapter diaryListAdapter;
    private LocalBroadcastManager mLBM;//接收
    private BroadcastReceiver DiaryReceiver = new BroadcastReceiver(){



        @Override
        public void onReceive(Context context, Intent intent) {

        }

        public <Intent> void onReceive(Context context, Intent intent) {
            refresh();
        }
    };
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();//监听，和DiaryActivity通信链接
        refresh();//刷新
    }
    //刷新
    private void refresh() {
        List<Diaryinfo> workinfos = Model.getInstance().getDiaryTable2().getDiaryByTitle();
        diaryListAdapter.refresh(workinfos);
    }

    private void initListener() {
        iv_main_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });
    }


    //获取数据
    private void initData() {
        diaryListAdapter = new DiaryListAdapter(MainActivity.this);
        lv_main_diary.setAdapter(diaryListAdapter);
        lv_main_diary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //填充数据
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Diaryinfo diaryinfo = (Diaryinfo) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DiaryDetailActivity.class);
                intent.putExtra("title", diaryinfo.getTitle());
                intent.putExtra("content", diaryinfo.getContent());
                startActivity(intent);
            }
        });
        //在同一个应用内的不同组件间发送Broadcast
        mLBM = LocalBroadcastManager.getInstance(MainActivity.this);
        // 当Intent在组件间传递时，组件如果想告知Android系统自己能够响应和处理哪些Intent，那么就需要用到IntentFilter对象。
        mLBM.registerReceiver(DiaryReceiver, new IntentFilter("diary_change"));//接收diary_change数据
        //绑定listview和contextmenu
        //要实现ListView的长按点击事件，可以直接调用setOnLongClickListener,在onLongClick里面实现逻辑
        registerForContextMenu(lv_main_diary);
    }

    //长按显示菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
        Diaryinfo diaryinfo = (Diaryinfo) lv_main_diary.getItemAtPosition(position);
        title = diaryinfo.getTitle();
        MainActivity.this.getMenuInflater().inflate(R.menu.delete, menu);//获得控件、显示菜单
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.diary_delete) {
            //调用model，实现删除
            Model.getInstance().getDiaryTable2().deleteDiary(title);
            refresh();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    //展示
    private void initView() {
        iv_main_write = findViewById(R.id.iv_main_write);
        lv_main_diary = findViewById(R.id.lv_main_diary);
    }

    //注销广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(DiaryReceiver);
    }
};
