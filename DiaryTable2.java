package com.swufe.work;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DiaryTable2 {
    private final DiaryDB mHelper;
    public DiaryTable2(Context context)
    {mHelper=new DiaryDB(context);}

    //添加日记
    public void saveDiary(Diaryinfo diaryinfo){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(DiaryTable.COL_ID,diaryinfo.getId());
        values.put(DiaryTable.COL_TITLE,diaryinfo.getTitle());
        values.put(DiaryTable.COL_CONTENT,diaryinfo.getContent());
        values.put(DiaryTable.COL_DATE,diaryinfo.getDate());
        db.replace(DiaryTable.TAB_NAME,null,values);
    }

    //获取日记
    public List<Diaryinfo> getDiaryByTitle(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql="select * from " + DiaryTable.TAB_NAME ;
        Cursor cursor = db.rawQuery(sql,null);
        List<Diaryinfo> diaryinfos=new ArrayList<>();
        while (cursor.moveToNext()){
            Diaryinfo diaryinfo=new Diaryinfo();
            diaryinfo.setId(cursor.getString(cursor.getColumnIndex(DiaryTable.COL_ID)));
            diaryinfo.setTitle(cursor.getString(cursor.getColumnIndex(DiaryTable.COL_TITLE)));
            diaryinfo.setContent(cursor.getString(cursor.getColumnIndex(DiaryTable.COL_CONTENT)));
            diaryinfo.setDate(cursor.getString(cursor.getColumnIndex(DiaryTable.COL_DATE)));
            diaryinfos.add(diaryinfo);
        }
        return diaryinfos;
    }

    //删除日记
    public void deleteDiary(String title){
        if (title==null){
            return;
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.delete(DiaryTable.TAB_NAME,DiaryTable.COL_TITLE+"=?",new String[]{title});
    }


}
