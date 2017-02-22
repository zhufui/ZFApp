package com.example.materialdesignsamples.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zf on 17-2-21.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public BaseRecyclerViewHolder(Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /*=========================下面是可能的控件操作===========================*/
    public BaseRecyclerViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyclerViewHolder setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public BaseRecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public BaseRecyclerViewHolder setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public BaseRecyclerViewHolder setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }

    public BaseRecyclerViewHolder setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public BaseRecyclerViewHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public BaseRecyclerViewHolder setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    public BaseRecyclerViewHolder setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }
}
