package com.swufe.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DiaryDB extends SQLiteOpenHelper {
    public DiaryDB(@Nullable Context context) {
        super(context, "diary.db", null, 1);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DiaryTable.CREATE_TAB);
    }

    //升级数据库，没有用上
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

//这里包括main文件里的一些代码有一些参考https://blog.csdn.net/bingocoder/article/details/80784802?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162420057916780366595590%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=162420057916780366595590&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-5-80784802.pc_search_result_cache&utm_term=Android%E8%AE%B0%E4%BA%8B%E6%9C%AC%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BF%9D%E5%AD%98&spm=1018.2226.3001.4187
