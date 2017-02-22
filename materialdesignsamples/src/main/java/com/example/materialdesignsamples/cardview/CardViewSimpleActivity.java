package com.example.materialdesignsamples.cardview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.materialdesignsamples.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zf on 17-2-17.
 */
public class CardViewSimpleActivity extends AppCompatActivity {
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_layout);
        ButterKnife.bind(this);

        initToolBar();

        //设置背景
        /*mCardView.setCardBackgroundColor(getColor(R.color.colorPrimary));
        //设置圆角
        mCardView.setRadius(5);
        //设置阴影
        mCardView.setCardElevation(3);
        //设置 padding
        mCardView.setUseCompatPadding(true);
        //
        mCardView.setPreventCornerOverlap(true);*/
    }

    private void initToolBar(){
        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.card_view)
    public void onClick() {
        Toast.makeText(this, "click cardview", Toast.LENGTH_SHORT).show();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CardViewSimpleActivity.class);
        return intent;
    }

}
