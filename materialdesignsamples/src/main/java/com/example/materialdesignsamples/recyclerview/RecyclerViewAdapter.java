package com.example.materialdesignsamples.recyclerview;

import android.content.Context;

import com.example.materialdesignsamples.R;

import java.util.List;

/**
 * Created by zf on 17-2-21.
 */
public class RecyclerViewAdapter extends BaseRecyclerViewAdapter {
    public RecyclerViewAdapter(Context context, int defaultLayoutId, List data) {
        super(context, defaultLayoutId, data);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null && position == mData.size() - 1) {
            if (mData.get(position).equals("last")) {
                return R.layout.loadmore;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        if (position == mData.size() - 1 && mData.get(position).equals("last")) {
            return;
        }
        helper.setText(R.id.tv, java.lang.String.valueOf((String) item));
    }

}
