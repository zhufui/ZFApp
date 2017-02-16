package cn.com.zfapp.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.logutils.LogUtils;
import cn.com.zfapp.R;
import cn.com.zfapp.manager.InitManager;

/**
 * Created by zf on 17-2-15.
 */
public class LogUtilAct extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_logutil);
        ButterKnife.bind(this);

        InitManager.initLogUtils();
    }

    @OnClick(R.id.button)
    public void onClick() {
        LogUtils.d("debug LogUtilAct");
        LogUtils.d("debug LogUtilAct %s log level is %d", "for example", 2);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LogUtilAct.class);
        return intent;
    }

}
