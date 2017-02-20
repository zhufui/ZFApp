package com.example.materialdesignsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.materialdesignsamples.cardview.CardViewSimpleActivity;
import com.example.materialdesignsamples.edit.TextInputSampleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                startActivity(TextInputSampleActivity.newIntent(this));
                break;
            case R.id.bt2:
                startActivity(CardViewSimpleActivity.newIntent(this));
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
        }
    }
}
