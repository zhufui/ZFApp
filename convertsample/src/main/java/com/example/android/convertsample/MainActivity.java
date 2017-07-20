package com.example.android.convertsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baseutils.ConvertUtils;
import com.example.baseutils.Utils;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    EditText px2dp_et;
    TextView px2dp_tv;

    EditText dp2px_et;
    TextView dp2px_tv;

    EditText sp2px_et;
    TextView sp2px_tv;

    EditText px2sp_et;
    TextView px2sp_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(this);
        mContext = this;
        px2dp_et = (EditText) findViewById(R.id.px2dp_et);
        px2dp_tv = (TextView) findViewById(R.id.px2dp_tv);
        px2dp_et.addTextChangedListener(TextWatcherHelper.getTextWatcher(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence)){
                    px2dp_tv.setText("");
                    return;
                }

                px2dp_tv.setText(String.valueOf(ConvertUtils.px2dp(Float.valueOf(charSequence.toString()))));
            }
        }));

        dp2px_et = (EditText) findViewById(R.id.dp2px_et);
        dp2px_tv = (TextView) findViewById(R.id.dp2px_tv);
        dp2px_et.addTextChangedListener(TextWatcherHelper.getTextWatcher(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence)){
                    dp2px_tv.setText("");
                    return;
                }

                dp2px_tv.setText(String.valueOf(
                        ConvertUtils.dp2px(mContext, Float.valueOf(charSequence.toString()))));
            }
        }));

        sp2px_et = (EditText) findViewById(R.id.sp2px_et);
        sp2px_tv = (TextView) findViewById(R.id.sp2px_tv);
        sp2px_et.addTextChangedListener(TextWatcherHelper.getTextWatcher(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence)){
                    sp2px_tv.setText("");
                    return;
                }

                sp2px_tv.setText(String.valueOf(ConvertUtils.sp2px(Float.valueOf(charSequence.toString()))));
            }
        }));

        px2sp_et = (EditText) findViewById(R.id.px2sp_et);
        px2sp_tv = (TextView) findViewById(R.id.px2sp_tv);
        px2sp_et.addTextChangedListener(TextWatcherHelper.getTextWatcher(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence)){
                    px2sp_tv.setText("");
                    return;
                }

                px2sp_tv.setText(String.valueOf(ConvertUtils.px2sp(Float.valueOf(charSequence.toString()))));
            }
        }));
    }

}
