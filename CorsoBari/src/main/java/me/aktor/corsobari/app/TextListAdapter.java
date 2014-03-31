package me.aktor.corsobari.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eto on 24/03/14.
 */
class TextListAdapter extends BaseAdapter {

    private ArrayList<String> mModel;
    private LayoutInflater mInflater;
    private int mCurrentFocus;

    TextListAdapter(Context context,ArrayList<String> model){
        mModel=model;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mModel!=null?mModel.size():0;
    }

    @Override
    public String getItem(int position) {
        return mModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView textView;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position==mCurrentFocus){
//            return Adapter.IGNORE_ITEM_VIEW_TYPE;
//
//        } else {
//            return 0;
//        }
//    }

    private boolean firstPass = true;
    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        ViewHolder h;
        if (recycledView == null){
            recycledView= mInflater.inflate(R.layout.row_layout,parent,false);
            h = new ViewHolder();
            h.textView = (TextView) recycledView.findViewById(R.id.tv_row);
            recycledView.setTag(h);
            if (firstPass){
                firstPass=false;
                h.textView.setTextColor(Color.RED);
            }
        } else {
            h = (ViewHolder)recycledView.getTag();
        }

        h.textView.setText(getItem(position));
        return recycledView;
    }

}
