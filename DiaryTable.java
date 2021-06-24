package com.swufe.work;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryTable extends AppCompatActivity {

    public static final String TAB_NAME="tab_diary";
    public static final String COL_ID="id";
    public static final String COL_TITLE="title";
    public static final String COL_CONTENT="content";
    public static final String COL_DATE="date";

    public static final String CREATE_TAB="create table "
            + TAB_NAME + " ("
            + COL_ID + " integer primary key autoincrement, "
            + COL_TITLE + " text,"
            + COL_CONTENT + " text,"
            + COL_DATE + " text);";
}