package com.example.materialdesignsamples.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zf on 17-2-21.
 */
public class RecyclerViewManager {
    public static void initRecyclerView(Context context, RecyclerView rv) {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
    }
}
