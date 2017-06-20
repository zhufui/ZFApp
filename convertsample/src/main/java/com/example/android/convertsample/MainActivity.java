package com.example.android.convertsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baseutils.ConvertUtils;

public class MainActivity extends AppCompatActivity {

    EditText px2dp_et;
    TextView px2dp_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        px2dp_et = (EditText) findViewById(R.id.px2dp_et);
        px2dp_tv = (TextView) findViewById(R.id.px2dp_tv);
        px2dp_et.addTextChangedListener(TextWatcherHelper.getTextWatcher(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                px2dp_tv.setText(ConvertUtils.px2dp(Float.valueOf((String) charSequence)));
            }
        }));
    }


}
