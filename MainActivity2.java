package com.swufe.work;

//MVC框架
//数据模型M(Model）存放数据
import android.content.Context;

import com.swufe.work.DiaryTable2;

class Model {
    private Context mContext;
    private static Model model=new Model();
    private DiaryTable2 diaryTable2;

    private Model(){}

    public static Model getInstance(){return model;}

    public void init(Context context){
        mContext=context;
        diaryTable2=new DiaryTable2(mContext);
    }

    public DiaryTable2 getDiaryTable2(){
        return diaryTable2;
    }

}
