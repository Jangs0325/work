package com.swufe.work;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//BaseAdapter适配器，在其中可以自定义一些很常用的功能，比如设置listview行内控件的点击方
class DiaryListAdapter extends BaseAdapter {
    private Context mContect;
    private List<Diaryinfo> mDiaryinfo=new ArrayList<>();
    private Diaryinfo diaryinfo;

    //刷新
    public void refresh(List<Diaryinfo> diaryinfos) {
        if (diaryinfos!=null && diaryinfos.size()>0){
            mDiaryinfo.clear();
            mDiaryinfo.addAll(diaryinfos);
            notifyDataSetChanged();
        }

    }

    public DiaryListAdapter(Context context){
        mContect=context;
    }

    @Override
    public int getCount() {
        return mDiaryinfo==null ? 0 : mDiaryinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mDiaryinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= View.inflate(mContect, R.layout.item_diarylist,null);
            holder.title=view.findViewById(R.id.tv_diarylist_name);
            holder.date=view.findViewById(R.id.tv_diarylist_date);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        diaryinfo = mDiaryinfo.get(position);

        holder.title.setText(diaryinfo.getTitle());
        holder.date.setText(diaryinfo.getDate());

        return view;
    }

    private class ViewHolder{
        TextView title;
        TextView date;
    }
}
