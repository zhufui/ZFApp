package cn.com.zfapp.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.com.zfapp.R;

/**
 * Created by zf on 17-2-15.
 */
public class NotificationAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_logutil);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NotificationAct.class);
        return intent;
    }

}
