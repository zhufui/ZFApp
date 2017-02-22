package com.example.materialdesignsamples.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.materialdesignsamples.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zf on 17-2-17.
 */
public class TextInputSampleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_input_layout_ac);
        ButterKnife.bind(this);

        initToolBar();
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

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TextInputSampleActivity.class);
        return intent;
    }

}
